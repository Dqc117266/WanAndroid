package com.dqc.base.presentation.viewmodel

interface BaseAction<State> {
    fun reduce(state: State): State
}