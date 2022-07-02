package kr.co.knowledgerally.ui.coach

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.Lesson
import kr.co.knowledgerally.domain.usecase.GetCoachLessonListUseCase
import javax.inject.Inject

@HiltViewModel
class CoachViewModel @Inject constructor(
    private val getCoachLessonListUseCase: GetCoachLessonListUseCase
) : BaseViewModel() {

    private val _tabState = MutableStateFlow(CoachTabState.Default)
    val tabState = _tabState.asStateFlow()

    private val _uiState = MutableStateFlow<CoachUiState>(CoachUiState.Loading)
    val uiState: StateFlow<CoachUiState> = _uiState.asStateFlow()

    init {
        fetchCoachLessons()
    }

    private fun fetchCoachLessons() {
        _uiState.value = CoachUiState.Loading
        launch {
            val result = getCoachLessonListUseCase()
            result
                .onSuccess { lessons ->
                    if (lessons.isNotEmpty()) {
                        _uiState.value = CoachUiState.Success(
                            matchingClasses = lessons
                                .filter { it.type == Lesson.Type.Matching }
                                .map { it.toCoachPresentation() as ClassUiState.Matching },
                            scheduledClasses = lessons
                                .filter { it.type == Lesson.Type.Scheduled }
                                .map { it.toCoachPresentation() as ClassUiState.Scheduled },
                            completedClasses = lessons
                                .filter { it.type == Lesson.Type.Completed }
                                .map { it.toCoachPresentation() as ClassUiState.Completed }
                        )
                    } else {
                        _uiState.value = CoachUiState.Empty
                    }
                }
                .onFailure { _uiState.value = CoachUiState.Failure }
        }
    }

    fun switchTab(newIndex: Int) {
        if (newIndex != tabState.value.currentIndex) {
            _tabState.update {
                it.copy(currentIndex = newIndex)
            }
        }
    }

    fun refresh() {
        fetchCoachLessons()
    }
}
