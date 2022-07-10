package kr.co.knowledgerally.ui.register.lounge

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterLoungeViewModel @Inject constructor(

) : BaseViewModel() {

    val uiState: StateFlow<RegisterLoungeUiState> = flow {
        emit(RegisterLoungeUiState.NoLecture)
    }
        .stateIn(viewModelScope, SharingStarted.Eagerly, RegisterLoungeUiState.Loading)
}
