package kr.co.knowledgerally.ui.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : BaseViewModel() {

    fun submitProfile(
        name: String,
        introduction: String,
        imageUri: String
    ) {
        // val profile = Profile(...)
    }
}
