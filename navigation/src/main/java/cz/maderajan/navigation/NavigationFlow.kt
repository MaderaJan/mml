package cz.maderajan.navigation

sealed class NavigationFlow {
    object SpotifySync : NavigationFlow()
    object Albums : NavigationFlow()
}