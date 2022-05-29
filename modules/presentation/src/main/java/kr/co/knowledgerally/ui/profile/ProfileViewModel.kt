package kr.co.knowledgerally.ui.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : BaseViewModel() {

    private val _name = MutableStateFlow(TextUiState.default(NAME_MAX_LENGTH))
    val name: StateFlow<TextUiState> = _name.asStateFlow()

    private val _introduction = MutableStateFlow(TextUiState.default(INTRODUCTION_MAX_LENGTH))
    val introduction: StateFlow<TextUiState> = _introduction.asStateFlow()

    fun updateName(name: String) {
        _name.update { it.update(name) }
    }

    fun updateIntroduction(introduction: String) {
        _introduction.update { it.update(introduction) }
    }

    fun uploadProfile() {
        // TODO: 프로필 업로드
    }

    companion object {
        private const val NAME_MAX_LENGTH = 10
        private const val INTRODUCTION_MAX_LENGTH = 100
    }
}
