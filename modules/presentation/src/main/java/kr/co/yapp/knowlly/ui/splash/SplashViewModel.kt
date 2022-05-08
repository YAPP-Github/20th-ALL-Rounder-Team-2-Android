package kr.co.yapp.knowlly.ui.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kr.co.yapp.knowlly.domain.usecase.IsLoggedInUseCase
import kr.co.yapp.knowlly.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isLoggedInUseCase: IsLoggedInUseCase
) : BaseViewModel() {

    private var _isLoggedInState: MutableStateFlow<LoggedInState> =
        MutableStateFlow(LoggedInState.NeedToLogin)
    val isLoggedInState: StateFlow<LoggedInState> = _isLoggedInState

    init {
        checkLogin()
    }

    private fun checkLogin() {
        this.launch {
            val result = isLoggedInUseCase()
            result
                .onSuccess {
                    _isLoggedInState.value =
                        if (it) LoggedInState.AlreadyLoggedIn else LoggedInState.NeedToLogin
                }
                .onFailure { handleException(it) }
        }
    }
}
