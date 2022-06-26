package kr.co.knowledgerally.ui.signup

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _result = MutableStateFlow<SignUpResult>(SignUpResult.Ready)
    val result = _result.asStateFlow()

    private var job: Job? = null

    fun signUp(pushActive: Boolean) {
        if (job?.isActive == true) {
            return
        }
        job = launch {
            val providerToken = ProviderToken.kakao(providerAccessToken)
            signUpUseCase(providerToken, pushActive)
                .onSuccess { _result.value = SignUpResult.Success }
                .onFailure { handleException(it) }
        }
    }

    companion object {
        const val KEY_PROVIDER_ACCESS_TOKEN = "PROVIDER_ACCESS_TOKEN"
    }
}
