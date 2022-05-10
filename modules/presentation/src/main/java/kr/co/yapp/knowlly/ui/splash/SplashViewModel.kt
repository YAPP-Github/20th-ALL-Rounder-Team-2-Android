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
    isLoggedInUseCase: IsLoggedInUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<SplashUiState>(SplashUiState.Unspecified)
    val state: StateFlow<SplashUiState> = _state.asStateFlow()

    init {
        launch {
            val isLoggedIn = isLoggedInUseCase().getOrThrow()
            if (isLoggedIn) {
                _state.value = SplashUiState.AlreadyLoggedIn
            } else {
                _state.value = SplashUiState.NeedToLogin
            }
        }
    }
}
