package cz.maderajan.ui.spotifysync.select

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import cz.maderajan.common.ui.fragment.viewBinding
import cz.maderajan.ui.spotifysync.R
import cz.maderajan.ui.spotifysync.databinding.FragmentSelectSpotifyAlbumsBinding
import cz.maderajan.ui.spotifysync.select.viewmodel.SelectSpotifyAlbumsViewModel
import cz.maderajan.ui.spotifysync.select.viewmodel.SyncSpotifyAlbums
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class SelectSpotifyAlbumsFragment : Fragment(R.layout.fragment_select_spotify_albums) {

    private val binding by viewBinding(FragmentSelectSpotifyAlbumsBinding::bind)
    private val viewModel: SelectSpotifyAlbumsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.send(SyncSpotifyAlbums)
    }
}