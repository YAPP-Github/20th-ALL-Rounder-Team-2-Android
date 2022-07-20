package kr.co.knowledgerally.ui.main

import android.webkit.CookieManager
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.bus.Event
import kr.co.knowledgerally.bus.EventBus
import kr.co.knowledgerally.domain.usecase.GetJwtTokenUseCase
import kr.co.knowledgerally.domain.usecase.GetUserStreamUseCase
import kr.co.knowledgerally.domain.usecase.IsWelcomeShownUseCase
import kr.co.knowledgerally.domain.usecase.RefreshUserUseCase
import kr.co.knowledgerally.domain.usecase.ShownWelcomeUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getUserStreamUseCase: GetUserStreamUseCase,
    private val getJwtTokenUseCase: GetJwtTokenUseCase,
    private val refreshUserUseCase: RefreshUserUseCase,
    private val isWelcomeShownUseCase: IsWelcomeShownUseCase,
    private val shownWelcomeUseCase: ShownWelcomeUseCase,
) : BaseViewModel() {
    private val _showWelcome = MutableStateFlow(false)
    val showWelcome: StateFlow<Boolean> = _showWelcome.asStateFlow()

    val user = getUserStreamUseCase()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    init {
        launch {
            refreshUserUseCase().getOrThrow()
        }

        launch {
            isWelcomeShownUseCase()
                .onSuccess { _showWelcome.value = !it }
        }

        manageCookie()
    }

    fun shownWelcome() {
        if (!showWelcome.value) {
            return
        }
        launch {
            shownWelcomeUseCase()
                .onSuccess { _showWelcome.value = false }
        }
    }

    fun onLectureRegistered() {
        launch {
            refreshUserUseCase().getOrThrow()
            EventBus.emit(Event.LectureRegistered)
        }
    }

    private fun manageCookie() {
        launch {
            val token = getJwtTokenUseCase().getOrThrow().accessToken

            val cookieManager = CookieManager.getInstance()
            cookieManager.removeAllCookies {}
            cookieManager.setAcceptCookie(true)

            cookieManager.setCookie("http://knowllydev-web.hkpark.net/", "X-AUTH-TOKEN=$token;")
            cookieManager.flush()
        }
    }
}
