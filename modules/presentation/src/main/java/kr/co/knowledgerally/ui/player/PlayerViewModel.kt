package kr.co.knowledgerally.ui.player

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.usecase.GetPlayerLectureInfoListUseCase
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getPlayerLectureInfoListUseCase: GetPlayerLectureInfoListUseCase,
) : BaseViewModel() {
    private val _tabState = MutableStateFlow(PlayerTabState.DEFAULT)
    val tabState: StateFlow<PlayerTabState> = _tabState.asStateFlow()

    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    init {
        fetch()
    }

    private fun fetch() = launch {
        getPlayerLectureInfoListUseCase()
            .onSuccess { _uiState.update { uiState -> uiState.from(it) } }
            .onFailure { handleException(it) }
    }

    fun switchTab(newIndex: Int) {
        if (newIndex != tabState.value.selectedTab.ordinal) {
            _tabState.update { it.copy(selectedTab = PlayerTabState.Tab.values()[newIndex]) }
        }
    }

    fun refresh() {
        _uiState.update { it.loading(true) }
        fetch()
    }
}
