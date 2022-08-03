package kr.co.knowledgerally.ui.review

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.constant.BASE_URL
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val lectureId: Long = savedStateHandle.get<Long>(KEY_LECTURE_ID)!!
    private val coachId: Long = savedStateHandle.get<Long>(KEY_COACH_ID)!!

    private val _url =
        MutableStateFlow("${BASE_URL}/review?lectureId=$lectureId&coachId=$coachId")
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
        const val KEY_LECTURE_ID = "KEY_LECTURE_ID"
        const val KEY_COACH_ID = "KEY_COACH_ID"
    }
}
