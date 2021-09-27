package cz.maderajan.common.ui

import androidx.navigation.NavDirections

interface IEffect

class SuccessEffect(val data: Any?) : IEffect {
    companion object {
        fun empty() = SuccessEffect(null)
    }
}

class NavDirectionEffect(val navDirection: NavDirections) : IEffect
object LoadingEffect : IEffect
object ReadyEffect : IEffect
class ErrorEffect(val message: Int) : IEffect