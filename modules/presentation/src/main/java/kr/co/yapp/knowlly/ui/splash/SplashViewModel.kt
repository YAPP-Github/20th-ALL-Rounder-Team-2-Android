package kr.co.yapp.knowlly.ui.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.yapp.knowlly.domain.usecase.IsLoggedInUseCase
import kr.co.yapp.knowlly.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isLoggedInUseCase: IsLoggedInUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<SplashUiState>(SplashUiState.Unspecified)
    val state: StateFlow<SplashUiState> = _state.asStateFlow()

    init {
        checkLogin()
    }

    private fun checkLogin() {
        launch {
            val result = isLoggedInUseCase()
            result
                .onSuccess {
                    _state.value =
                        if (it) SplashUiState.AlreadyLoggedIn else SplashUiState.NeedToLogin
                }
                .onFailure { handleException(it) }
        }
    }
}
