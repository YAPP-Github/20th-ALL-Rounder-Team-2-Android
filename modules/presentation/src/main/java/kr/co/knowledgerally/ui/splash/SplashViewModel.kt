package kr.co.knowledgerally.ui.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
            val job = launch { delay(MIN_SPLASH_TIME) }
            val isLoggedIn = isLoggedInUseCase().getOrThrow()
            job.join()

            if (isLoggedIn) {
                val isOnboarded = isOnboardedUseCase().getOrThrow()
                _uiState.value =
                    if (isOnboarded) SplashUiState.AlreadyLoggedIn else SplashUiState.NeedToOnboard
            } else {
                _uiState.value = SplashUiState.Tutorial
            }
        }
    }

    companion object {
        private const val MIN_SPLASH_TIME = 2000L
    }
}
