package kr.co.knowledgerally.ui.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.model.KakaoProfile
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.NotLoggedIn)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun login(kakaoProfile: KakaoProfile?) {
        if (kakaoProfile != null) {
            //
            // 서비스 로그인
            //
            _state.value = LoginState.Success
        }
        failToLogin()
    }

    fun failToLogin() {
        _state.value = LoginState.Failure
    }
}
