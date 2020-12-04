package com.muffls.tap.main.ui

import androidx.lifecycle.viewModelScope
import com.example.common.android.resource.ResourceProvider
import com.muffls.tap.main.business.domain.AddTapUseCase
import com.muffls.tap.main.business.domain.LevelData
import com.muffls.tap.main.business.domain.LevelDataUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

class TapViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val addTapUseCase: AddTapUseCase,
    private val levelDataUseCase: LevelDataUseCase
) : BaseViewModel<TapViewModel.ViewState, TapViewModel.Action>() {

    init {
        viewModelScope.launch {
            dispatch(Action.InitialState)
            levelDataUseCase.data.onEach { levelData ->
                dispatch(Action.LevelDataLoaded(levelData))
            }.launchIn(this)
        }
    }

    override var currentState: ViewState = createState("0", 0, 0)

    override fun reduce(action: Action): ViewState {
        return when (action) {
            is Action.InitialState -> createState("0", 0, 0)
            is Action.LevelDataLoaded -> {
                val levelData = action.levelData
                createState(
                    levelData.tapCount.toString(),
                    levelData.progress.roundToInt(),
                    levelData.level
                )
            }
        }
    }

    fun addTap() = viewModelScope.launch {
        addTapUseCase.addTap()
    }

    private fun createState(tapCount: String, progress: Int, level: Int) =
        ViewState(tapCount, progress, "${resourceProvider.getString(R.string.tap_level)} $level")

    data class ViewState(
        val tapCount: String,
        val progress: Int,
        val level: String
    )

    sealed class Action {
        object InitialState : Action()
        data class LevelDataLoaded(val levelData: LevelData) : Action()
    }
}
