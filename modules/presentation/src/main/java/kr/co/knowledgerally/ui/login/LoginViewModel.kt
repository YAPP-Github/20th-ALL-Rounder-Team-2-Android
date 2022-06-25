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
    
    suspend fun login(accessToken: String) {
        val providerToken = ProviderToken.kakao(accessToken)
        val isSignedUp = isSignedUpUseCase(providerToken).getOrThrow()

        if (isSignedUp) {
            signInUseCase(providerToken)
                .onSuccess {
                    val isOnboarded = isOnboardedUseCase().getOrThrow()
                    _state.value =
                        if (isOnboarded) LoginState.Success else LoginState.NeedToOnboard
                }
        } else {
            _state.value = LoginState.NeedToSignUp(providerToken.accessToken)
        }
        _state.value = LoginState.NeedToSignUp(accessToken)
    }
}
