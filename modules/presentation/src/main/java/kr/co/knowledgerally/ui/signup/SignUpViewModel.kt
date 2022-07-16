package kr.co.knowledgerally.ui.signup

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.ProviderToken
import kr.co.knowledgerally.domain.usecase.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val signUpUseCase: SignUpUseCase,
) : BaseViewModel() {
    private val providerAccessToken =
        requireNotNull(savedStateHandle.get<String>(KEY_PROVIDER_ACCESS_TOKEN)) {
            "$KEY_PROVIDER_ACCESS_TOKEN 값이 없습니다"
        }

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    fun signUp() {
        if (job != null) return

        job = launch {
            _uiState.update { it.copy(isLoading = true) }
            val providerToken = ProviderToken.kakao(providerAccessToken)
            val isSuccess = signUpUseCase(providerToken)
                .map { true }
                .onFailure { handleException(it) }
                .getOrDefault(false)

            _uiState.update {
                it.copy(
                    isLoading = false,
                    result = SignUpResult(isSuccess)
                )
            }
        }
        job?.invokeOnCompletion { job = null }
    }

    companion object {
        const val KEY_PROVIDER_ACCESS_TOKEN = "PROVIDER_ACCESS_TOKEN"
    }
}
