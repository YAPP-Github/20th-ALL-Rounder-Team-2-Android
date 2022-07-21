package kr.co.knowledgerally.ui.mypage

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.VersionName
import kr.co.knowledgerally.domain.usecase.GetUserStreamUseCase
import kr.co.knowledgerally.domain.usecase.LogoutUseCase
import kr.co.knowledgerally.domain.usecase.RefreshUserUseCase
import kr.co.knowledgerally.domain.usecase.WithdrawalUseCase
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    versionName: VersionName,
    getUserStreamUseCase: GetUserStreamUseCase,
    private val refreshUserUseCase: RefreshUserUseCase,
    private val withdrawalUseCase: WithdrawalUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(
        MyPageUiState(versionName = versionName.toString())
    )
    val uiState = _uiState.asStateFlow()

    init {
        launch {
            getUserStreamUseCase().collect { user ->
                _uiState.update {
                    it.copy(user = user)
                }
            }
        }
    }

    fun refresh() {
        launch {
            refreshUserUseCase().getOrThrow()
        }
    }

    fun logout() {
        launch {
            _uiState.update { it.copy(isLoading = true) }
            val isSuccess = logoutUseCase()
                .map { true }
                .onFailure { handleException(it) }
                .getOrDefault(false)

            _uiState.update {
                it.copy(
                    isLoading = false,
                    isSignOut = isSuccess,
                )
            }
        }
    }

    // 회원 탈퇴
    fun withdrawal() {
        launch {
            _uiState.update { it.copy(isLoading = true) }
            val isSuccess = withdrawalUseCase()
                .map { true }
                .onFailure { handleException(it) }
                .getOrDefault(false)

            _uiState.update {
                it.copy(
                    isLoading = false,
                    isSignOut = isSuccess,
                )
            }
        }
    }

    companion object {
        const val PRIVACY_POLICY_URL =
            "https://sunsetmood.notion.site/Knowlly-6293f0a979e64afbb220ebc5d0e1519f"
        const val TERMS_OF_SERVICE_URL =
            "https://sunsetmood.notion.site/Knowlly-ad0dfa18936743729c240af88df170f0"
    }
}
