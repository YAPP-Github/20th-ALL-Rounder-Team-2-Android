package kr.co.knowledgerally.ui.player

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor() : BaseViewModel() {

    private val _tabState = MutableStateFlow(PlayerTabState.DEFAULT)
    val tabState: StateFlow<PlayerTabState> = _tabState.asStateFlow()

    val uiState: StateFlow<PlayerUiState> = flow {
        // TODO: Fetch player's class list
        emit(
            PlayerUiState.Success(
                matchingLesson = listOf(
                    LessonUiState.Matching(lessonId = 0),
                    LessonUiState.Matching(lessonId = 2)
                ),
                scheduledLesson = listOf(
                    LessonUiState.Scheduled(lessonId = 3, kakaoId = "kakaoId"),
                    LessonUiState.Scheduled(lessonId = 4, kakaoId = "kakaoId"),
                ),
                completedLesson = listOf(
                    LessonUiState.Completed(lessonId = 5, isReviewed = true),
                    LessonUiState.Completed(lessonId = 6, isReviewed = false)
                )
            )
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, PlayerUiState.Loading)

    fun switchTab(newIndex: Int) {
        if (newIndex != tabState.value.selectedTab.ordinal) {
            _tabState.update { it.copy(selectedTab = PlayerTabState.Tab.values()[newIndex]) }
        }
    }
}
