package kr.co.knowledgerally.ui.applicant

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.constant.BASE_URL
import javax.inject.Inject

@HiltViewModel
class ApplicantViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val lectureInfoId: Long = savedStateHandle.get<Long>(KEY_LECTURE_INFO_ID)!!

    private val _url =
        MutableStateFlow("${BASE_URL}/coach-lecture/on-board/$lectureInfoId")
    val url = _url.asStateFlow()

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    fun refresh() {
        _isRefresh.value = true
    }

    fun onRefresh() {
        _isRefresh.value = false
    }

    companion object {
        const val KEY_LECTURE_INFO_ID = "KEY_LECTURE_INFO_ID"
    }
}
