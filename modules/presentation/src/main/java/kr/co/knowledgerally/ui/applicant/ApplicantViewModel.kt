package kr.co.knowledgerally.ui.applicant

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ApplicantViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val classId: String = savedStateHandle.get<String>(KEY_CLASS_ID)!!

    companion object {
        const val KEY_CLASS_ID = "KEY_CLASS_ID"
    }
}
