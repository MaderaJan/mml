package cz.maderajan.navigation.direction

import cz.maderajan.navigation.NavigationCommand

object SpotifyDirection {

    val root = object : NavigationCommand {

        override val destination: String = "spotify.root"
    }

    val intro = object : NavigationCommand {

        override val destination: String = "spotify.intro"
    }

    val selectAlbums = object : NavigationCommand {

        override val destination: String = "spotify.select.albums"
    }

    val filter = object : NavigationCommand {

        override val destination: String = "spotify.filter"
    }
}