package cz.maderajan.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow

class NavigationFlowBus {
    var navigationFlow = Channel<NavigationCommand>(Channel.UNLIMITED)

    fun consume(): Flow<NavigationCommand> {
        navigationFlow = Channel(Channel.UNLIMITED)
        return navigationFlow.consumeAsFlow()
    }

    suspend fun send(direction: NavigationCommand) {
        navigationFlow.send(direction)
    }
}