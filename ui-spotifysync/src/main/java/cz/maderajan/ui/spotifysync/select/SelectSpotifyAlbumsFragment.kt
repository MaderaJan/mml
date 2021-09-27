package cz.maderajan.ui.spotifysync.select

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.reddit.indicatorfastscroll.FastScrollItemIndicator
import cz.maderajan.common.ui.fragment.viewBinding
import cz.maderajan.mml.commonutil.ErrorEffect
import cz.maderajan.mml.commonutil.LoadingEffect
import cz.maderajan.mml.commonutil.ReadyEffect
import cz.maderajan.mml.commonutil.SuccessEffect
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.data.select.AlphabetLetter
import cz.maderajan.ui.spotifysync.data.select.SelectableAlbum
import cz.maderajan.ui.spotifysync.databinding.FragmentSelectSpotifyAlbumsBinding
import cz.maderajan.ui.spotifysync.select.adapter.SelectableAlbumAdapter
import cz.maderajan.ui.spotifysync.select.viewmodel.*
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

        setHasOptionsMenu(true)
        binding.topAppBar.inflateMenu(R.menu.menu_done)
        binding.topAppBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_done -> {
                    viewModel.send(SaveSelectedAlbums)
                    true
                }
                else -> false
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SelectableAlbumAdapter { album ->
            viewModel.send(AlbumClicked(album))
        }
        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                adapter.submitList(it.albums)
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.uiEffect.consumeAsFlow()
                .collect { effect ->
                    binding.errorScreen.isVisible = effect is ErrorEffect
                    binding.progressBar.isVisible = effect is LoadingEffect

                    if (effect is ReadyEffect) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.selectAllBannerView.isVisible = true
                        }, 500)
                    }

                    if (effect is SuccessEffect) {
                        // TODO ALBUM SELECTION
                    }
                }
        }

        binding.errorScreen.setActionButtonClick {
            viewModel.send(SyncSpotifyAlbums)
        }

        setupFastScrollerWithAlphabet(adapter)
        setupBanner()

        viewModel.send(SyncSpotifyAlbums)
    }

    private fun setupBanner() {
        binding.selectAllBannerView.setNegativeButtonClick {
            binding.selectAllBannerView.isVisible = false
        }

        binding.selectAllBannerView.setPositiveButtonClick {
            viewModel.send(SelectAllAlbums)
            binding.selectAllBannerView.isVisible = false
        }
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