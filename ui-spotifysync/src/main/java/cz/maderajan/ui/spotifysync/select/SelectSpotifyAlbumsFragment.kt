package cz.maderajan.ui.spotifysync.select

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cz.maderajan.common.ui.UiEffect
import cz.maderajan.common.ui.fragment.viewBinding
import cz.maderajan.navigation.NavigationFlow
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.databinding.FragmentSelectSpotifyAlbumsBinding
import cz.maderajan.ui.spotifysync.select.adapter.SelectableAlbumAdapter
import cz.maderajan.ui.spotifysync.select.viewmodel.SelectSpotifyAlbumsActions
import cz.maderajan.ui.spotifysync.select.viewmodel.SelectSpotifyAlbumsViewModel
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

        setHasOptionsMenu(true)
        binding.topAppBar.inflateMenu(R.menu.menu_done)
        binding.topAppBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_done -> {
                    viewModel.send(SelectSpotifyAlbumsActions.SaveSelectedAlbums)
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
            viewModel.send(SelectSpotifyAlbumsActions.AlbumClicked(album))
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
                    binding.errorScreen.isVisible = effect is UiEffect.ErrorUiEffect
                    binding.progressBar.isVisible = effect is UiEffect.LoadingUiEffect

                    if (effect is UiEffect.ReadyUiEffect) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            binding.selectAllBannerView.isVisible = true
                        }, 500)
                    }

                    if (effect is UiEffect.SuccessUiEffect) {
                        viewModel.navigationFlowBus.send(NavigationFlow.Albums)
                    }
                }
        }

        binding.errorScreen.setActionButtonClick {
            viewModel.send(SelectSpotifyAlbumsActions.SyncSpotifyAlbums)
        }

        setupBanner()

        viewModel.send(SelectSpotifyAlbumsActions.SyncSpotifyAlbums)
    }

    private fun setupBanner() {
        binding.selectAllBannerView.setNegativeButtonClick {
            binding.selectAllBannerView.isVisible = false
        }

        binding.selectAllBannerView.setPositiveButtonClick {
            viewModel.send(SelectSpotifyAlbumsActions.SelectAllAlbums)
            binding.selectAllBannerView.isVisible = false
        }
    }
}