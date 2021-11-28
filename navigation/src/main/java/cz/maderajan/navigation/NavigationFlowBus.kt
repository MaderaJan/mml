package cz.maderajan.navigation

import kotlinx.coroutines.channels.Channel

class NavigationFlowBus {
    val navigationFlow = Channel<NavigationFlow>(Channel.UNLIMITED)

    suspend fun send(direction: NavigationFlow) {
        navigationFlow.send(direction)
    }
}