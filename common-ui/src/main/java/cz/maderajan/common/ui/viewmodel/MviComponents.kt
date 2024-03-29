package cz.maderajan.common.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.maderajan.common.ui.UiEffect
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface BaseViewState

interface IAction

abstract class BaseMviViewModel<S : BaseViewState, A : IAction>(initState: S) : ViewModel() {

    private val _state = MutableStateFlow(initState)
    val state: StateFlow<S>
        get() = _state

    val uiEffect = Channel<UiEffect>(Channel.UNLIMITED)

    protected val actions = Channel<A>(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            handleActions()
        }
    }

    protected abstract suspend fun handleActions()
    protected open fun onActionError(error: Throwable) {}

    fun send(action: A) {
        viewModelScope.launch {
            actions.send(action)
        }
    }

    protected suspend fun sendEffect(effect: UiEffect) {
        uiEffect.send(effect)
    }

    fun setState(reducer: S.() -> S) {
        _state.value = reducer(_state.value)
    }
}
