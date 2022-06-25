package kr.co.knowledgerally.ui.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.ProviderToken
import kr.co.knowledgerally.domain.usecase.IsSignedUpUseCase
import kr.co.knowledgerally.domain.usecase.SignInUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val isSignedUpUseCase: IsSignedUpUseCase,
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.NotLoggedIn)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    /**
     * TODO
     * 1. 현재 AccessToken이 가입된 유저인지 검사
     * 2. 가입되어 있다면 LoginUseCase 호출
     * 3. 가입되어 있지 않다면 SignUp으로 이동
     */
    suspend fun login(accessToken: String) {
        val providerToken = ProviderToken.kakao(accessToken)
        val isSignedUp = isSignedUpUseCase(providerToken).getOrThrow()

        if (isSignedUp) {
            // TODO: 온보딩 진행 여부에 따른 state 분기
        } else {
            _state.value = LoginState.NeedToSignUp(providerToken.accessToken)
        }
        _state.value = LoginState.NeedToSignUp(accessToken)
    }
}
