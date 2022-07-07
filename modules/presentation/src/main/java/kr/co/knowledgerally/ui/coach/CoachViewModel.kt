package kr.co.knowledgerally.ui.coach

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.usecase.GetCoachLectureBundleUseCase
import javax.inject.Inject

@HiltViewModel
class CoachViewModel @Inject constructor(
    private val getCoachLectureBundleUseCase: GetCoachLectureBundleUseCase
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
            val result = getCoachLectureBundleUseCase()
            result
                .onSuccess { lectures ->
                    if (lectures.isEmpty) {
                        _uiState.value = CoachUiState.Empty
                    } else {
                        _uiState.value = CoachUiState.Success(
                            matchingLectures = lectures.onboardingLectures.map { it.toCoachUiState() as CoachLectureUiState.Matching },
                            scheduledLectures = lectures.ongoingLectures.map { it.toCoachUiState() as CoachLectureUiState.Scheduled },
                            completedLectures = lectures.doneLectures.map { it.toCoachUiState() as CoachLectureUiState.Completed },
                        )
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
