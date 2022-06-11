package kr.co.knowledgerally.ui.main

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.usecase.IsWelcomeShownUseCase
import kr.co.knowledgerally.domain.usecase.ShownWelcomeUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isWelcomeShownUseCase: IsWelcomeShownUseCase,
    private val shownWelcomeUseCase: ShownWelcomeUseCase,
) : BaseViewModel() {

    private val _showWelcome = MutableStateFlow(false)
    val showWelcome: StateFlow<Boolean> = _showWelcome.asStateFlow()

    init {
        launch {
            isWelcomeShownUseCase()
                .onSuccess { _showWelcome.value = !it }
        }
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
}
