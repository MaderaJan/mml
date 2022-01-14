package cz.maderajan.navigation.direction

import cz.maderajan.navigation.NavigationCommand

object SpotifyDirections {

    val root = object : NavigationCommand {

        override val destination: String = "spotify.root"
    }

    val default = object : NavigationCommand {

        override val destination: String = ""
    }

    val intro = object : NavigationCommand {

        override val destination: String = "intro"
    }

    val selectAlbums = object : NavigationCommand {

        override val destination: String = "select.albums"
    }

    val albums = object : NavigationCommand {

        override val destination: String = "albums"
    }
}