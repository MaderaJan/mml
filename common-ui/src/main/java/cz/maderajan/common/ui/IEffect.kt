package cz.maderajan.common.ui

import androidx.navigation.NavDirections
import cz.maderajan.navigation.NavigationFlow

interface IEffect

class SuccessEffect(val data: Any?) : IEffect {
    companion object {
        fun empty() = SuccessEffect(null)
    }
}

class NavDirectionEffect(val navDirection: NavDirections) : IEffect
class NavFlowEffect(val navFlow: NavigationFlow) : IEffect
object LoadingEffect : IEffect
object ReadyEffect : IEffect
class ErrorEffect(val message: Int) : IEffect