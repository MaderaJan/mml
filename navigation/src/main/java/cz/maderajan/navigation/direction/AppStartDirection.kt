package cz.maderajan.navigation.direction

import cz.maderajan.navigation.NavigationCommand

object AppStartDirection {

    val root = object : NavigationCommand {

        override val destination: String = "app.start.root"
    }

    val appStart = object : NavigationCommand {

        override val destination: String = "app.start.default"
    }
}