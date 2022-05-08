package kr.co.yapp.knowlly.ui.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.co.yapp.knowlly.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    fun login(platform: LoginPlatform.Type) {
        when (platform) {
            LoginPlatform.Type.GOOGLE -> {}
            LoginPlatform.Type.KAKAO -> {}
        }
    }
}
