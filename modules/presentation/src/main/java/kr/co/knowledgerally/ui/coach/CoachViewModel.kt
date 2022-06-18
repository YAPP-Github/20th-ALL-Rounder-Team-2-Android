package kr.co.knowledgerally.ui.coach

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
class CoachViewModel @Inject constructor() : BaseViewModel() {

    private val _tabState = MutableStateFlow(CoachTabState.Default)
    val tabState = _tabState.asStateFlow()

    val uiState: StateFlow<CoachUiState> = flow {
        emit(
            CoachUiState.Success(
                matchingClasses = listOf(
                    ClassUiState.Matching(classId = "0", "프랑스어", emptyList()),
                    ClassUiState.Matching(classId = "1", "프랑스어", emptyList()),
                ),
                scheduledClasses = listOf(
                    ClassUiState.Scheduled,
                    ClassUiState.Scheduled,
                    ClassUiState.Scheduled,
                    ClassUiState.Scheduled,
                    ClassUiState.Scheduled,
                ),
                completedClasses = listOf(
                    ClassUiState.Completed,
                    ClassUiState.Completed,
                    ClassUiState.Completed,
                    ClassUiState.Completed,
                    ClassUiState.Completed,
                )
            )
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, CoachUiState.Loading)

    fun switchTab(newIndex: Int) {
        if (newIndex != tabState.value.currentIndex) {
            _tabState.update {
                it.copy(currentIndex = newIndex)
            }
        }
    }

    fun refresh() {
        // TODO
    }
}
