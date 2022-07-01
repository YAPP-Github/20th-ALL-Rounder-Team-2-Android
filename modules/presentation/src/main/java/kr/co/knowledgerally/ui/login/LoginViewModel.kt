package kr.co.knowledgerally.ui.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.ProviderToken
import kr.co.knowledgerally.domain.usecase.IsOnboardedUseCase
import kr.co.knowledgerally.domain.usecase.IsSignedUpUseCase
import kr.co.knowledgerally.domain.usecase.SignInUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val isSignedUpUseCase: IsSignedUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val isOnboardedUseCase: IsOnboardedUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.NotLoggedIn)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun login(accessToken: String) = launch {
        _loading.value = true
        val providerToken = ProviderToken.kakao(accessToken)
        val isSignedUp = isSignedUpUseCase(providerToken).getOrThrow()

        if (!isSignedUp) {
            _state.value = LoginState.NeedToSignUp(accessToken)
            return@launch
        }

        signInUseCase(providerToken)
            .onSuccess {
                val isOnboarded = isOnboardedUseCase().getOrThrow()
                _state.value =
                    if (isOnboarded) LoginState.Success else LoginState.NeedToOnboard
            }
            .onFailure {
                handleException(it)
            }
    }

    override fun handleException(throwable: Throwable) {
        _loading.value = false
        super.handleException(throwable)
    }
}
