package cz.maderajan.mml

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import cz.maderajan.common.resources.MmlTheme
import cz.maderajan.common.ui.appstart.AppStartScreen
import cz.maderajan.navigation.NavigationFlowBus
import cz.maderajan.navigation.direction.AlbumsDirection
import cz.maderajan.navigation.direction.AppStartDirection
import cz.maderajan.navigation.direction.SpotifyDirection
import cz.maderajan.ui.spotifysync.intro.SpotifyIntroScreen
import cz.maderajan.ui.spotifysync.select.SelectSpotifyAlbumsScreen
import cz.maderajan.uialbums.ui.AlbumListScreen
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel

// TODO spotify sync opravit

class MainActivity : AppCompatActivity() {

    // TODO REMOVE private val navigator: Navigator by inject()
    private val navigationFlowBus: NavigationFlowBus by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContent {
            MmlTheme {
                val navController = rememberNavController()
                navigationFlowBus.navigationFlow.collectAsState().value.also { navigationFlow ->
                    if (navigationFlow.destination.isNotEmpty()) {
                        navController.navigate(navigationFlow.destination)
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
                        composable(SpotifyDirection.intro.destination) {
                            SpotifyIntroScreen(viewModel = getViewModel())
                        }
                        composable(SpotifyDirection.selectAlbums.destination) {
                            SelectSpotifyAlbumsScreen(viewModel = getViewModel())
                        }
                    }

                    // ALBUM GRAPH
                    navigation(
                        route = AlbumsDirection.root.destination,
                        startDestination = AlbumsDirection.albums.destination,
                    ) {
                        composable(AlbumsDirection.albums.destination) {
                            AlbumListScreen()
                        }
                    }
                }
            }
        }

        // TODO REMOVE
//        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
//        navigator.navController = navController
//
//        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            val conf = NavigationViewDataSource.data.firstOrNull { it.fragmentId == destination.id }
//            bottomNavigation.isVisible = conf!!.showBottomNavigation
//        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == REQ_SPOTIFY_LOGIN) {
//            val response = AuthorizationClient.getResponse(resultCode, data)
//            when (response.type) {
//                AuthorizationResponse.Type.TOKEN -> viewModel.send(IntroSpotifyAction.PersistSpotifyLoginToken(response.accessToken))
//                AuthorizationResponse.Type.ERROR -> Timber.e(response.error)
//                else -> Timber.e(response.state)
//            }
//        }
//    }
}