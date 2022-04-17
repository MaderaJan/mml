package cz.maderajan.navigation

import kotlinx.coroutines.channels.Channel

class NavigationFlowBus {
    val navigationFlow = Channel<NavigationCommand>(Channel.UNLIMITED)

    suspend fun send(direction: NavigationCommand) {
        navigationFlow.send(direction)
    }
}