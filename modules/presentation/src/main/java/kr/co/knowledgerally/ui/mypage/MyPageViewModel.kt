package kr.co.knowledgerally.ui.mypage

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.VersionName
import kr.co.knowledgerally.domain.usecase.LogoutUseCase
import kr.co.knowledgerally.domain.usecase.WithdrawalUseCase
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    versionName: VersionName,
    private val withdrawalUseCase: WithdrawalUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel() {

    private val _state: MutableStateFlow<MyPageUiState> = MutableStateFlow(MyPageUiState.Loading)
    val state = _state.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut = _isLoggedOut.asStateFlow()

    init {
        // TODO: 유저 정보 가져오기
        _state.value = MyPageUiState.Success(
            notificationEnabled = false,
            versionName = versionName.toString(),
            userName = "Laco",
            isCoach = true,
            remainingBallCount = 0,
        )
    }

    fun updateNotificationEnabled(enabled: Boolean) {
        val state = state.value
        if (state is MyPageUiState.Success) {
            _state.value = state.copy(enabled)
        }
    }

    fun logout() {
        _loading.value = true
        launch {
            logoutUseCase()
                .onSuccess { _isLoggedOut.value = true }
                .onFailure {
                    _loading.value = false
                    handleException(it)
                }
        }
    }

    // 회원 탈퇴
    fun withdrawal() {
        _loading.value = true
        launch {
            withdrawalUseCase()
                .onSuccess { _isLoggedOut.value = true }
                .onFailure {
                    _loading.value = false
                    handleException(it)
                }
        }
    }
}
