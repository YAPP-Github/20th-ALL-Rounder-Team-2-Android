package kr.co.knowledgerally.ui.ball

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.usecase.GetBallHistoryListUseCase
import kr.co.knowledgerally.domain.usecase.GetUserStreamUseCase
import kr.co.knowledgerally.ui.model.toPresentation
import javax.inject.Inject

@HiltViewModel
class BallViewModel @Inject constructor(
    getUserStreamUseCase: GetUserStreamUseCase,
    private val getBallHistoryListUseCase: GetBallHistoryListUseCase
) : BaseViewModel() {

    val uiState: StateFlow<BallUiState> = combine(
        getUserStreamUseCase(),
        flow {
            getBallHistoryListUseCase()
                .onSuccess { emit(it) }
                .onFailure { handleException(it) }
        }
    ) { user, histories ->
        BallUiState.Success(user.ballCount, histories)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, BallUiState.Loading)
}
