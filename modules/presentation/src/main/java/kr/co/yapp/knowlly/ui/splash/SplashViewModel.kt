package kr.co.yapp.knowlly.ui.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kr.co.yapp.knowlly.domain.usecase.IsLoggedInUseCase
import kr.co.yapp.knowlly.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isLoggedInUseCase: IsLoggedInUseCase
) : BaseViewModel() {

    private var _splashUiState: MutableStateFlow<SplashUiState> =
        MutableStateFlow(SplashUiState.Checking)
    val isLoggedInState: StateFlow<SplashUiState> = _splashUiState

    init {
        checkLogin()
    }

    private fun checkLogin() {
        launch {
            val result = isLoggedInUseCase()
            result
                .onSuccess {
                    _splashUiState.value =
                        if (it) SplashUiState.AlreadyLoggedIn else SplashUiState.NeedToLogin
                }
                .onFailure { handleException(it) }
        }
    }
}
