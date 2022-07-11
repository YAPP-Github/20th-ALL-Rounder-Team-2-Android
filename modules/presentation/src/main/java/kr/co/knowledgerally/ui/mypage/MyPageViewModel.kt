package kr.co.knowledgerally.ui.mypage

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.VersionName
import kr.co.knowledgerally.domain.usecase.GetUserStreamUseCase
import kr.co.knowledgerally.domain.usecase.LogoutUseCase
import kr.co.knowledgerally.domain.usecase.RefreshUserUseCase
import kr.co.knowledgerally.domain.usecase.UpdatePushActiveUseCase
import kr.co.knowledgerally.domain.usecase.WithdrawalUseCase
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    versionName: VersionName,
    getUserStreamUseCase: GetUserStreamUseCase,
    private val refreshUserUseCase: RefreshUserUseCase,
    private val updatePushActiveUseCase: UpdatePushActiveUseCase,
    private val withdrawalUseCase: WithdrawalUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel() {
    val uiState: StateFlow<MyPageUiState> = getUserStreamUseCase().map { user ->
        MyPageUiState.Success(
            user = user,
            versionName = versionName.toString()
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, MyPageUiState.Loading)

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut = _isLoggedOut.asStateFlow()

    fun updatePushActive(active: Boolean) {
        launch {
            updatePushActiveUseCase(active).getOrThrow()
            refresh()
        }
    }
    
    fun refresh() {
        launch {
            refreshUserUseCase().getOrThrow()
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
