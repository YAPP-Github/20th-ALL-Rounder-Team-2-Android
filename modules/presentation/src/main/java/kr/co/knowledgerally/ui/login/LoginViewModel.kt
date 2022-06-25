package kr.co.knowledgerally.ui.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.NotLoggedIn)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    /**
     * TODO
     * 1. 현재 AccessToken이 가입된 유저인지 검사
     * 2. 가입되어 있다면 LoginUseCase 호출
     * 3. 가입되어 있지 않다면 SignUp으로 이동
     */
    fun login(accessToken: String) {
        _state.value = LoginState.NeedToSignUp(accessToken)
    }
}
