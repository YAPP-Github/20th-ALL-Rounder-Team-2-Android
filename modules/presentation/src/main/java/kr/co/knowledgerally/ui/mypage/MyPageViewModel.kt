package kr.co.knowledgerally.ui.mypage

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.VersionName
import kr.co.knowledgerally.domain.usecase.GetUserUseCase
import kr.co.knowledgerally.domain.usecase.LogoutUseCase
import kr.co.knowledgerally.domain.usecase.WithdrawalUseCase
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    versionName: VersionName,
    getUserUseCase: GetUserUseCase,
    private val withdrawalUseCase: WithdrawalUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<MyPageUiState> = MutableStateFlow(MyPageUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut = _isLoggedOut.asStateFlow()

    init {
        launch {
            val user = getUserUseCase().getOrThrow()
            _uiState.value = MyPageUiState.Success(
                user = user,
                versionName = versionName.toString()
            )
        }
    }

    fun updatePushActive(active: Boolean) {
        val state = uiState.value
        if (state !is MyPageUiState.Success) {
            return
        }

        val newUser = state.user.copy(pushActive = active)
        _uiState.value = state.copy(user = newUser)

        launch {
            // TODO: Patch User
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
