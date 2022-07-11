package kr.co.knowledgerally.ui.register.lounge

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.LectureState
import kr.co.knowledgerally.domain.usecase.GetCoachLectureInfoListUseCase
import javax.inject.Inject

@HiltViewModel
class RegisterLoungeViewModel @Inject constructor(
    getCoachLectureInfoListUseCase: GetCoachLectureInfoListUseCase,
) : BaseViewModel() {

    val uiState: StateFlow<RegisterLoungeUiState> = flow {
        getCoachLectureInfoListUseCase(LectureState.Onboard)
            .map { lectureInfoList ->
                if (lectureInfoList.isEmpty()) {
                    RegisterLoungeUiState.Lectures(lectureInfoList)
                } else {
                    RegisterLoungeUiState.NoLecture
                }
            }
            .onSuccess { emit(it) }
            .onFailure { handleException(it) }
    }
        .stateIn(viewModelScope, SharingStarted.Eagerly, RegisterLoungeUiState.Loading)
}
