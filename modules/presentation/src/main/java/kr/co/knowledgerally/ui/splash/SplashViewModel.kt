package kr.co.knowledgerally.ui.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.usecase.IsLoggedInUseCase
import kr.co.knowledgerally.domain.usecase.IsOnboardedUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    isLoggedInUseCase: IsLoggedInUseCase,
    isOnboardedUseCase: IsOnboardedUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        launch {
            val isLoggedIn = isLoggedInUseCase().getOrThrow()
            if (isLoggedIn) {
                val isOnboarded = isOnboardedUseCase().getOrThrow()
                _uiState.value =
                    if (isOnboarded) SplashUiState.AlreadyLoggedIn else SplashUiState.NeedToOnboard
            } else {
                _uiState.value = SplashUiState.NeedToLogin
            }
        }
    }
}
