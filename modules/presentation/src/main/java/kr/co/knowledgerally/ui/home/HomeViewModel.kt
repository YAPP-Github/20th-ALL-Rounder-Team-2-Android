package kr.co.knowledgerally.ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): BaseViewModel() {

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    fun refresh() {
        _isRefresh.value = true
    }

    fun onRefresh() {
        _isRefresh.value = false
    }
}
