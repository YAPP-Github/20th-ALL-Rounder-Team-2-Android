package kr.co.knowledgerally.ui.player

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.usecase.GetPlayerLectureListUseCase
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getPlayerLectureListUseCase: GetPlayerLectureListUseCase
) : BaseViewModel() {

    private val _tabState = MutableStateFlow(PlayerTabState.DEFAULT)
    val tabState: StateFlow<PlayerTabState> = _tabState.asStateFlow()

    private val _uiState = MutableStateFlow<PlayerUiState>(PlayerUiState.Loading)
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    init {
        fetchCoachLectures()
    }

    private fun fetchCoachLectures() {
        _uiState.value = PlayerUiState.Loading
        launch {
            val result = getPlayerLectureListUseCase()
            result
                .onSuccess { lectures ->
                    _uiState.value = PlayerUiState.Success(
                        matchingLectures = lectures
                            .filter { it.type == Lecture.Type.Matching }
                            .map { it.toPlayerPresentation() as PlayerLectureModel.Matching },
                        scheduledLectures = lectures
                            .filter { it.type == Lecture.Type.Scheduled }
                            .map { it.toPlayerPresentation() as PlayerLectureModel.Scheduled },
                        completedLectures = lectures
                            .filter { it.type == Lecture.Type.Completed }
                            .map { it.toPlayerPresentation() as PlayerLectureModel.Completed }
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
