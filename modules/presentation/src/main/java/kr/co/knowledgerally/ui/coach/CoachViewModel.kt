package kr.co.knowledgerally.ui.coach

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CoachViewModel @Inject constructor() : BaseViewModel() {

    val uiState: StateFlow<CoachUiState> = flow {
        emit(CoachUiState.Empty)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, CoachUiState.Loading)
}
