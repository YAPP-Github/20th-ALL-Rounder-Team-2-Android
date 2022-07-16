package kr.co.knowledgerally.ui.lecture

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LectureViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val lectureInfoId: Long = savedStateHandle.get<Long>(KEY_LECTURE_INFO_ID)!!

    private val _url =
        MutableStateFlow("${BASE_URL}/lecture-detail/$lectureInfoId")
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
        private const val BASE_URL = "http://knowllydev-web.hkpark.net"
        const val KEY_LECTURE_INFO_ID = "KEY_LECTURE_INFO_ID"
    }
}
