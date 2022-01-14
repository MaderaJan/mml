package cz.maderajan.navigation

import cz.maderajan.navigation.direction.SpotifyDirections
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationFlowBus {
    val navigationFlow = MutableStateFlow(SpotifyDirections.default)

    suspend fun send(direction: NavigationCommand) {
        navigationFlow.emit(direction)
    }
}