package com.droidos.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * Base class for ViewModels that handles state management using StateFlow.
 *
 * @param T The type of the screen state.
 * @param E The type of the screen event.
 * @property initialVal The initial value of the screen state.
 */
abstract class BaseViewModel<T : ScreenState, in E : ScreenEvent>(initialVal: T) : ViewModel() {

    private var _uiState = MutableStateFlow(initialVal)
    val uiState: StateFlow<T> get() = _uiState


    fun emitEvent(event: E) {
        reduce(_uiState.value, event)
    }

    fun createNewState(newState: T) {
        _uiState.tryEmit(newState)
    }

    abstract fun reduce(oldState: T, sideEffect: E)
}

interface ScreenState
interface ScreenEvent