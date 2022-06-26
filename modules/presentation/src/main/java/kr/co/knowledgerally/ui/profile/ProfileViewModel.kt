package kr.co.knowledgerally.ui.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.core.exception.ImageException
import kr.co.knowledgerally.domain.model.Onboard
import kr.co.knowledgerally.domain.usecase.SubmitOnboardUseCase
import kr.co.knowledgerally.toast.Toaster
import kr.co.knowledgerally.ui.R
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val submitOnboardUseCase: SubmitOnboardUseCase,
) : BaseViewModel() {
    private val _completed = MutableStateFlow(false)
    val completed = _completed.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private var job: Job? = null

    fun submit(
        name: String,
        introduction: String,
        kakaoId: String,
        portfolio: String?,
        imageUri: String?
    ) {
        if (job?.isActive == true) {
            return
        }
        _loading.value = true
        launch {
            val onboard = Onboard(
                username = name,
                intro = introduction,
                kakaoId = kakaoId,
                portfolio = portfolio.takeUnless { it.isNullOrBlank() },
                imageUri = imageUri,
            )

            submitOnboardUseCase(onboard)
                .onSuccess { _completed.value = true }
                .onFailure { handleException(it) }
        }
    }

    override fun handleException(throwable: Throwable) {
        _loading.value = false
        when (throwable) {
            is ImageException -> Toaster.show(R.string.exception_image)
            else -> super.handleException(throwable)
        }
    }
}
