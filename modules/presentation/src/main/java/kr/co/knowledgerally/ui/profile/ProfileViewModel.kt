package kr.co.knowledgerally.ui.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.core.exception.ImageTranscodeException
import kr.co.knowledgerally.domain.model.Onboard
import kr.co.knowledgerally.domain.usecase.GetUserStreamUseCase
import kr.co.knowledgerally.domain.usecase.ModifyOnboardUseCase
import kr.co.knowledgerally.domain.usecase.RefreshUserUseCase
import kr.co.knowledgerally.domain.usecase.SubmitOnboardUseCase
import kr.co.knowledgerally.toast.Toaster
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.profile.state.CompleteState
import kr.co.knowledgerally.ui.profile.state.Mode
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getUserStreamUseCase: GetUserStreamUseCase,
    private val refreshUserUseCase: RefreshUserUseCase,
    private val submitOnboardUseCase: SubmitOnboardUseCase,
    private val modifyOnboardUseCase: ModifyOnboardUseCase,
) : BaseViewModel() {

    val mode: Mode = savedStateHandle.get<Mode>(KEY_MODE)!!

    private val _completed = MutableStateFlow<CompleteState>(CompleteState.Waiting)
    val completed = _completed.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private var job: Job? = null

    val user = when (mode) {
        Mode.New -> null
        Mode.Edit -> getUserStreamUseCase().stateIn(viewModelScope, SharingStarted.Eagerly, null)
    }

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

            when (mode) {
                Mode.New -> {
                    submitOnboardUseCase(onboard)
                        .onSuccess { _completed.value = CompleteState.Created }
                        .onFailure { handleException(it) }
                }
                Mode.Edit -> {
                    modifyOnboardUseCase(onboard)
                        .onSuccess { _completed.value = CompleteState.Modified }
                        .onFailure { handleException(it) }
                }
            }
        }
    }

    fun refreshUser() {
        launch {
            refreshUserUseCase().getOrThrow()
        }
    }

    override fun handleException(throwable: Throwable) {
        _loading.value = false
        when (throwable) {
            is ImageTranscodeException -> Toaster.show(R.string.exception_image)
            else -> super.handleException(throwable)
        }
    }

    companion object {
        const val KEY_MODE = "KEY_MODE"
    }
}
