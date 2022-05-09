package kr.co.yapp.knowlly.ui.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.co.yapp.knowlly.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    fun login(type: LoginType) {
        when (type) {
            LoginType.GOOGLE -> {}
            LoginType.KAKAO -> {}
        }
    }
}
