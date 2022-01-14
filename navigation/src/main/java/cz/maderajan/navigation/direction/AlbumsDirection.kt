package cz.maderajan.navigation.direction

import cz.maderajan.navigation.NavigationCommand

object AlbumsDirection {

    val root = object : NavigationCommand {

        override val destination: String = "albums.root"
    }

    val albums = object : NavigationCommand {

        override val destination: String = "albums"
    }
}