package kr.co.knowledgerally.ui.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.feature.kakao.KakaoAccessToken
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.NotLoggedIn)
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun login(token: KakaoAccessToken) {
        _state.value = LoginState.Success
    }
}
