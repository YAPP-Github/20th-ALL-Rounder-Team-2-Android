package kr.co.knowledgerally.ui.coach

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.bus.Event
import kr.co.knowledgerally.bus.EventBus
import kr.co.knowledgerally.domain.usecase.GetCoachLectureInfoListUseCase
import kr.co.knowledgerally.domain.usecase.GetUserStreamUseCase
import javax.inject.Inject

@HiltViewModel
class CoachViewModel @Inject constructor(
    private val getUserStreamUseCase: GetUserStreamUseCase,
    private val getCoachLectureInfoListUseCase: GetCoachLectureInfoListUseCase,
) : BaseViewModel() {
    private val _tabState = MutableStateFlow(CoachTabState.Default)
    val tabState = _tabState.asStateFlow()

    private val _uiState = MutableStateFlow(CoachUiState())
    val uiState: StateFlow<CoachUiState> = _uiState.asStateFlow()

    init {
        launch {
            val user = getUserStreamUseCase().first()
            if (user.coach) {
                fetch()
            } else {
                _uiState.update { it.init() }
            }
        }
        launch {
            EventBus.event
                .filterIsInstance<Event.LectureRegistered>()
                .collect { refresh() }
        }
    }

    private fun fetch() = launch {
        getCoachLectureInfoListUseCase()
            .onSuccess { _uiState.update { uiState -> uiState.from(it) } }
            .onFailure { handleException(it) }
    }

    fun switchTab(newIndex: Int) {
        if (newIndex != tabState.value.currentIndex) {
            _tabState.update {
                it.copy(currentIndex = newIndex)
            }
        }
    }

    fun refresh() {
        _uiState.update { it.loading(true) }
        fetch()
    }
}
