package kr.co.knowledgerally.ui.player

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.Lesson
import kr.co.knowledgerally.domain.usecase.GetPlayerLessonListUseCase
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getPlayerLessonListUseCase: GetPlayerLessonListUseCase
) : BaseViewModel() {

    private val _tabState = MutableStateFlow(PlayerTabState.DEFAULT)
    val tabState: StateFlow<PlayerTabState> = _tabState.asStateFlow()

    private val _uiState = MutableStateFlow<PlayerUiState>(PlayerUiState.Loading)
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    init {
        fetchCoachLessons()
    }

    private fun fetchCoachLessons() {
        _uiState.value = PlayerUiState.Loading
        launch {
            val result = getPlayerLessonListUseCase()
            result
                .onSuccess { lessons ->
                    _uiState.value = PlayerUiState.Success(
                        matchingLessons = lessons
                            .filter { it.type == Lesson.Type.Matching }
                            .map { it.toPlayerPresentation() as PlayerLessonModel.Matching },
                        scheduledLessons = lessons
                            .filter { it.type == Lesson.Type.Scheduled }
                            .map { it.toPlayerPresentation() as PlayerLessonModel.Scheduled },
                        completedLessons = lessons
                            .filter { it.type == Lesson.Type.Completed }
                            .map { it.toPlayerPresentation() as PlayerLessonModel.Completed }
                    )
                }
                .onFailure { _uiState.value = PlayerUiState.Failure }
        }
    }

    fun switchTab(newIndex: Int) {
        if (newIndex != tabState.value.selectedTab.ordinal) {
            _tabState.update { it.copy(selectedTab = PlayerTabState.Tab.values()[newIndex]) }
        }
    }
}
