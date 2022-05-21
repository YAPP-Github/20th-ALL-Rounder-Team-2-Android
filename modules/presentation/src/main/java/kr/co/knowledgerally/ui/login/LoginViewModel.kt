package kr.co.knowledgerally.ui.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.model.SocialUserInfo
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    fun login(socialUserInfo: SocialUserInfo?) {
        if (socialUserInfo != null) {
            //
            // usecase 통해 서비스 로그인
            //

            _isLoggedIn.value = true
        } else {
            // 로그인 실패
        }
    }
}
