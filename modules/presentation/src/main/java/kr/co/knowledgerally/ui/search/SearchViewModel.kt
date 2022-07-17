package kr.co.knowledgerally.ui.search

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _url =
        MutableStateFlow("${BASE_URL}/lecture-search")
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
    }
}
