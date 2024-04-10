package com.dqc.base.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dqc.base.BuildConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

abstract class BaseViewModel<State : BaseState, Action : BaseAction<State>>(initialState: State) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(initialState)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    private var stateTimeTraveDebugger: StateTimeTravelDebugger? = null

    init {
        if (BuildConfig.DEBUG) {
            stateTimeTraveDebugger = StateTimeTravelDebugger(this::class.java.simpleName)
        }
    }

    private var state by Delegates.observable(initialState) {_, old, new ->
        if (old != new) {
            viewModelScope.launch {
                _uiStateFlow.value = new
            }

            stateTimeTraveDebugger?.apply {
                addStateTransition(old, new)
                logLast()
            }
        }
    }

    protected fun sendAction(action: Action) {
        stateTimeTraveDebugger?.addAction(action)
        state = action.reduce(state)
    }

}