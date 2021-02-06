package cz.maderajan.ui.spotifysync.select

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cz.maderajan.common.ui.fragment.viewBinding
import cz.maderajan.mml.commonutil.LoadingEffect
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.databinding.FragmentSelectSpotifyAlbumsBinding
import cz.maderajan.ui.spotifysync.select.adapter.SelectableAlbumAdapter
import cz.maderajan.ui.spotifysync.select.viewmodel.SelectSpotifyAlbumsViewModel
import cz.maderajan.ui.spotifysync.select.viewmodel.SyncSpotifyAlbums
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

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

        val adapter = SelectableAlbumAdapter()
        binding.recyclerView.adapter = adapter

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
}