package cz.maderajan.common.ui

import androidx.navigation.NavDirections
import cz.maderajan.navigation.NavigationFlow

interface UiEffect {

    object LoadingUiEffect : UiEffect
    object ReadyUiEffect : UiEffect
    class ErrorUiEffect(val message: Int) : UiEffect
    class SuccessUiEffect(val data: Any?) : UiEffect {
        companion object {
            fun empty() = SuccessUiEffect(null)
        }
    }

    class NavDirectionUiEffect(val navDirection: NavDirections) : UiEffect
    class NavFlowUiEffect(val navFlow: NavigationFlow) : UiEffect
}