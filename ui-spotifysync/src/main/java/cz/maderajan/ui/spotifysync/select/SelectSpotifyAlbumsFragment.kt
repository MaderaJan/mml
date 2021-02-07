package cz.maderajan.ui.spotifysync.select

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.indicatorfastscroll.FastScrollItemIndicator
import cz.maderajan.common.ui.fragment.viewBinding
import cz.maderajan.mml.commonutil.LoadingEffect
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.data.select.AlphabetLetter
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum
import cz.maderajan.ui.spotifysync.databinding.FragmentSelectSpotifyAlbumsBinding
import cz.maderajan.ui.spotifysync.select.adapter.SelectableAlbumAdapter
import cz.maderajan.ui.spotifysync.select.viewmodel.AlbumClicked
import cz.maderajan.ui.spotifysync.select.viewmodel.SelectSpotifyAlbumsViewModel
import cz.maderajan.ui.spotifysync.select.viewmodel.SyncSpotifyAlbums
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

@ExperimentalCoroutinesApi
class SelectSpotifyAlbumsFragment : Fragment(R.layout.fragment_select_spotify_albums) {

    private val binding by viewBinding(FragmentSelectSpotifyAlbumsBinding::bind)
    private val viewModel: SelectSpotifyAlbumsViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SelectableAlbumAdapter { album ->
            viewModel.send(AlbumClicked(album))
        }
        binding.recyclerView.adapter = adapter

        setupFastScrollerWithAlphabet(adapter)

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                adapter.submitList(it.albums)
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.uiEffect.consumeAsFlow()
                .collect { effect ->
                    binding.progressBar.isVisible = effect is LoadingEffect
                }
        }

        viewModel.send(SyncSpotifyAlbums)
    }

    private fun setupFastScrollerWithAlphabet(adapter: SelectableAlbumAdapter) {
        binding.fastScroller.setupWithRecyclerView(
            binding.recyclerView,
            { position ->
                val item = adapter.getItemPosition(position)
                val title = when (item) {
                    is SelectableAlbum -> item.name
                    is AlphabetLetter -> item.letter
                    else -> ""
                }.toString()

                FastScrollItemIndicator.Text(title.substring(0, 1).toUpperCase(Locale.getDefault()))
            }
        )

        binding.fastscrollerThumb.setupWithFastScroller(binding.fastScroller)
    }
}