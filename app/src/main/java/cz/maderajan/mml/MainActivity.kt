package cz.maderajan.mml

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.spotify.sdk.android.auth.AuthorizationResponse
import cz.maderajan.common.resources.MmlTheme
import cz.maderajan.common.resources.PrimaryColor900
import cz.maderajan.common.ui.appstart.AppStartScreen
import cz.maderajan.navigation.NavigationFlowBus
import cz.maderajan.navigation.direction.AlbumsDirection
import cz.maderajan.navigation.direction.AppStartDirection
import cz.maderajan.navigation.direction.SpotifyDirection
import cz.maderajan.ui.spotifysync.filter.SelectSpotifyFilterScreen
import cz.maderajan.ui.spotifysync.intro.SpotifyIntroScreen
import cz.maderajan.ui.spotifysync.intro.viewmodel.IntroSpotifyAction
import cz.maderajan.ui.spotifysync.intro.viewmodel.IntroSpotifySyncViewModel
import cz.maderajan.ui.spotifysync.select.SelectSpotifyAlbumsScreen
import cz.maderajan.uialbums.ui.list.AlbumListScreen
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    private val navigationFlowBus: NavigationFlowBus by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContent {
            val navController = rememberNavController()

            MmlTheme {
                Surface(color = PrimaryColor900) {
                    lifecycleScope.launchWhenCreated {
                        navigationFlowBus.consume().collect { navigationFlow ->
                            navController.navigate(navigationFlow.destination) {
                                when (navigationFlow.destination) {
                                    AlbumsDirection.root.destination -> {
                                        popUpTo(AppStartDirection.root.destination) { inclusive = true }
                                    }
                                }
                            }
                        }
                    }

                    NavHost(
                        navController = navController,
                        startDestination = AppStartDirection.root.destination
                    ) {

                        // APP START GRAPH
                        navigation(
                            route = AppStartDirection.root.destination,
                            startDestination = AppStartDirection.appStart.destination
                        ) {
                            composable(AppStartDirection.appStart.destination) {
                                AppStartScreen(viewModel = getViewModel())
                            }
                        }

                        // SPOTIFY GRAPH
                        navigation(
                            route = SpotifyDirection.root.destination,
                            startDestination = SpotifyDirection.intro.destination,
                        ) {
                            composable(
                                route = SpotifyDirection.intro.destination,
                            ) {
                                SpotifyIntroScreen(viewModel = getViewModel())
                            }
                            composable(SpotifyDirection.selectAlbums.destination) {
                                SelectSpotifyAlbumsScreen(viewModel = getViewModel())
                            }
                            composable(SpotifyDirection.filter.destination) {
                                SelectSpotifyFilterScreen(
                                    navController = navController,
                                    viewModel = getViewModel()
                                )
                            }
                        }

                        // ALBUM GRAPH
                        navigation(
                            route = AlbumsDirection.root.destination,
                            startDestination = AlbumsDirection.albums.destination,
                        ) {
                            composable(AlbumsDirection.albums.destination) {
                                AlbumListScreen(
                                    viewModel = getViewModel()
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val uri = intent?.data ?: return
        val response = AuthorizationResponse.fromUri(uri)
        val introSpotifySyncViewModel: IntroSpotifySyncViewModel = getViewModel()
        introSpotifySyncViewModel.send(IntroSpotifyAction.SpotifyResponse(response))
    }
}