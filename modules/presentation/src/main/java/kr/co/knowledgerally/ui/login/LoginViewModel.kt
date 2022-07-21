package kr.co.knowledgerally.ui.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.ProviderToken
import kr.co.knowledgerally.domain.usecase.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    fun login(accessToken: String) {
        if (job != null) {
            return
        }
        job = launch {
            _uiState.update { it.copy(isLoading = true) }
            val providerToken = ProviderToken.kakao(accessToken)
            val result = loginUseCase(providerToken)
                .onFailure { handleException(it) }
                .getOrNull()

            _uiState.update {
                it.copy(isLoading = false, result = result)
            }
        }
        job?.invokeOnCompletion { job = null }
    }

    companion object {
        const val PRIVACY_POLICY_URL =
            "https://sunsetmood.notion.site/Knowlly-6293f0a979e64afbb220ebc5d0e1519f"
        const val TERMS_OF_SERVICE_URL =
            "https://sunsetmood.notion.site/Knowlly-ad0dfa18936743729c240af88df170f0"
    }
}
