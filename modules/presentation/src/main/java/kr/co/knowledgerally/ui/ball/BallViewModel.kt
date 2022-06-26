package kr.co.knowledgerally.ui.ball

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.usecase.GetBallHistoryListUseCase
import kr.co.knowledgerally.domain.usecase.GetUserStreamUseCase
import kr.co.knowledgerally.ui.model.toPresentation
import javax.inject.Inject

@HiltViewModel
class BallViewModel @Inject constructor(
    private val getUserStreamUseCase: GetUserStreamUseCase,
    private val getBallHistoryListUseCase: GetBallHistoryListUseCase
) : BaseViewModel() {

    val user = getUserStreamUseCase()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val _state = MutableStateFlow<BallUiState>(BallUiState.Loading)
    val state = _state.asStateFlow()

    init {
        fetchBallHistoryList()
    }

    private fun fetchBallHistoryList() {
        _state.value = BallUiState.Loading
        launch {
            val result = getBallHistoryListUseCase()
            result
                .mapCatching { it.map { history -> history.toPresentation() } }
                .onSuccess { if (it.isNotEmpty()) BallUiState.Success(it) else BallUiState.Empty }
                .onFailure { BallUiState.Failure }
        }
    }
}