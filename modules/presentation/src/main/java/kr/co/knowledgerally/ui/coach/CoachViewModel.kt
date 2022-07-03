package kr.co.knowledgerally.ui.coach

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.usecase.GetCoachLectureListUseCase
import javax.inject.Inject

@HiltViewModel
class CoachViewModel @Inject constructor(
    private val getCoachLectureListUseCase: GetCoachLectureListUseCase
) : BaseViewModel() {

    private val _tabState = MutableStateFlow(CoachTabState.Default)
    val tabState = _tabState.asStateFlow()

    private val _uiState = MutableStateFlow<CoachUiState>(CoachUiState.Loading)
    val uiState: StateFlow<CoachUiState> = _uiState.asStateFlow()

    init {
        fetchCoachLectures()
    }

    private fun fetchCoachLectures() {
        _uiState.value = CoachUiState.Loading
        launch {
            val result = getCoachLectureListUseCase()
            result
                .onSuccess { lectures ->
                    if (lectures.isNotEmpty()) {
                        _uiState.value = CoachUiState.Success(
                            matchingLectures = lectures
                                .filter { it.type == Lecture.Type.Matching }
                                .map { it.toCoachPresentation() as CoachLectureModel.Matching },
                            scheduledLectures = lectures
                                .filter { it.type == Lecture.Type.Scheduled }
                                .map { it.toCoachPresentation() as CoachLectureModel.Scheduled },
                            completedLectures = lectures
                                .filter { it.type == Lecture.Type.Completed }
                                .map { it.toCoachPresentation() as CoachLectureModel.Completed }
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
        fetchCoachLectures()
    }
}
