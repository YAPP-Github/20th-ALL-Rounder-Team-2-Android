package kr.co.knowledgerally.ui.register.lounge

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
        delay(1500L)
        emit(RegisterLoungeUiState.Lectures(""))
    }
        .stateIn(viewModelScope, SharingStarted.Eagerly, RegisterLoungeUiState.Loading)
}
