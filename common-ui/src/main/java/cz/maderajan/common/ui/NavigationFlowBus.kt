package cz.maderajan.common.ui

import cz.maderajan.navigation.NavigationFlow
import kotlinx.coroutines.channels.Channel

class NavigationFlowBus {
    val navigationFlow = Channel<NavigationFlow>(Channel.UNLIMITED)

    suspend fun send(direction: NavigationFlow) {
        navigationFlow.send(direction)
    }
}