package cz.maderajan.navigation

import cz.maderajan.navigation.direction.AppStartDirection
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationFlowBus {
    val navigationFlow = MutableStateFlow(AppStartDirection.default)

    suspend fun send(direction: NavigationCommand) {
        navigationFlow.emit(direction)
    }
}