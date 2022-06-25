package kr.co.knowledgerally.ui.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : BaseViewModel() {

    private val _isComplete = MutableStateFlow(false)
    val isComplete = _isComplete.asStateFlow()

    fun submitProfile(
        name: String,
        introduction: String,
        imageUri: String
    ) {
        _isComplete.value = true
        // val profile = Profile(...)
    }
}
