package kr.co.knowledgerally.ui.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.core.exception.ImageTranscodeException
import kr.co.knowledgerally.domain.model.Onboard
import kr.co.knowledgerally.domain.usecase.GetUserStreamUseCase
import kr.co.knowledgerally.domain.usecase.ModifyOnboardUseCase
import kr.co.knowledgerally.domain.usecase.SubmitOnboardUseCase
import kr.co.knowledgerally.toast.Toaster
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.profile.state.Mode
import kr.co.knowledgerally.ui.profile.state.OnboardResult
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getUserStreamUseCase: GetUserStreamUseCase,
    private val submitOnboardUseCase: SubmitOnboardUseCase,
    private val modifyOnboardUseCase: ModifyOnboardUseCase,
) : BaseViewModel() {

    val mode: Mode = savedStateHandle.get<Mode>(KEY_MODE)!!

    private val _uiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(
        ProfileUiState(
            isLoading = mode == Mode.Edit,
        )
    )
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private var job: Job? = null

    val user = when (mode) {
        Mode.New -> emptyFlow()
        Mode.Edit -> getUserStreamUseCase()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun submit(
        name: String,
        introduction: String,
        kakaoId: String,
        portfolio: String?,
        imageUri: String?
    ) {
        if (job != null) return
        _uiState.update { it.copy(isLoading = true) }

        job = launch {
            val onboard = Onboard(
                username = name,
                intro = introduction,
                kakaoId = kakaoId,
                portfolio = portfolio,
                imageUri = imageUri.takeIf { it != user.value?.profile?.imageUrl }
            )

            val result = when (mode) {
                Mode.New -> submitOnboard(onboard)
                Mode.Edit -> modifyOnboard(onboard)
            }
            _uiState.update {
                it.copy(
                    isLoading = false,
                    result = result.getOrNull()
                )
            }
        }
        job?.invokeOnCompletion { job = null }
    }

    private suspend fun submitOnboard(onboard: Onboard): Result<OnboardResult> =
        submitOnboardUseCase(onboard)
            .map { OnboardResult.Created }

    private suspend fun modifyOnboard(onboard: Onboard): Result<OnboardResult> =
        modifyOnboardUseCase(onboard)
            .map { OnboardResult.Modified }

    override fun handleException(throwable: Throwable) {
        when (throwable) {
            is ImageTranscodeException -> Toaster.show(R.string.exception_image)
            else -> super.handleException(throwable)
        }
    }

    companion object {
        const val KEY_MODE = "KEY_MODE"
    }
}
