package kr.co.knowledgerally.ui.applicant

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.usecase.GetApplicantListUseCase
import javax.inject.Inject

@HiltViewModel
class ApplicantViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getApplicantListUseCase: GetApplicantListUseCase,
) : BaseViewModel() {
    private val classId: String = savedStateHandle.get<String>(KEY_CLASS_ID)!!

    val uiState: StateFlow<ApplicantUiState> = flow {
        delay(1500L)
        getApplicantListUseCase()
            .onSuccess {
                if (it.isNotEmpty()) {
                    emit(ApplicantUiState.Applicants(it))
                } else {
                    emit(ApplicantUiState.Empty)
                }
            }
    }
        .stateIn(viewModelScope, SharingStarted.Eagerly, ApplicantUiState.Loading)

    companion object {
        const val KEY_CLASS_ID = "KEY_CLASS_ID"
    }
}
