package cz.maderajan.navigation

import androidx.navigation.NavController

class Navigator {
    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: NavigationFlow) {
        when (navigationFlow) {
            NavigationFlow.SpotifySync -> navController.navigate(MainNavGraphDirections.actionGlobalSpotifysyncFlow())
            NavigationFlow.Albums -> navController.navigate(MainNavGraphDirections.actionGlobalAlbumsFlow())
        }
    }

}