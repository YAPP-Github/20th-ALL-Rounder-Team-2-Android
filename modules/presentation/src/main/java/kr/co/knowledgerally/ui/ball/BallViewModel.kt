package kr.co.knowledgerally.ui.ball

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.usecase.GetBallHistoryListUseCase
import kr.co.knowledgerally.domain.usecase.GetBallUseCase
import kr.co.knowledgerally.ui.model.BallCountModel
import kr.co.knowledgerally.ui.model.toPresentation
import javax.inject.Inject

@HiltViewModel
class BallViewModel @Inject constructor(
    private val getBallUseCase: GetBallUseCase,
    private val getBallHistoryListUseCase: GetBallHistoryListUseCase
) : BaseViewModel() {

    private val _ball = MutableStateFlow(BallCountModel(""))
    val ball = _ball.asStateFlow()

    private val _state = MutableStateFlow<BallUiState>(BallUiState.Loading)
    val state = _state.asStateFlow()

    init {
        fetchBall()
        fetchBallHistoryList()
    }

    private fun fetchBall() {
        launch {
            val result = getBallUseCase()
            result
                .mapCatching { it.toPresentation() }
                .onSuccess { _ball.value = it }
                .onFailure { /* no-op */ }
        }
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