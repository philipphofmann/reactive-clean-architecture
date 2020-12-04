package com.muffls.tap.main.ui

import androidx.lifecycle.ViewModel
import io.sentry.Sentry
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

abstract class BaseViewModel<State, Action> : ViewModel() {

    private val stateTransitions: MutableList<StateTransition<Action, State>> = mutableListOf()

    protected abstract var currentState: State
    private val _state: ConflatedBroadcastChannel<State> = ConflatedBroadcastChannel()
    val state: Flow<State> = _state.asFlow()

    fun dispatch(action: Action) {
        val newState = reduce(action)

        if (BuildConfig.DEBUG) {
            stateTransitions.add(StateTransition(action, currentState, newState))

            Sentry.captureMessage("Changing state\n" +
                    "Action:  $action\n" +
                    "Current: $currentState\n" +
                    "New:     $newState")

        }

        currentState = newState
        _state.sendBlocking(newState)
    }

    abstract fun reduce(action: Action): State

    private data class StateTransition<Action, State>(
        val action: Action,
        val current: State,
        val new: State
    )
}