package cz.maderajan.common.ui

import androidx.navigation.NavController
import cz.maderajan.navigation.MainNavGraphDirections
import cz.maderajan.navigation.NavigationFlow

class Navigator() {

    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: NavigationFlow) {
        when (navigationFlow) {
            NavigationFlow.SpotifySync -> navController.navigate(MainNavGraphDirections.actionGlobalSpotifysyncFlow())
            NavigationFlow.Albums -> navController.navigate(MainNavGraphDirections.actionGlobalAlbumsFlow())
        }
    }

}