package kr.co.knowledgerally.ui.user

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val userId: Long = savedStateHandle.get<Long>(KEY_USER_ID)!!

    val url: String = "${BASE_URL}/profile/$userId"

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
        const val KEY_USER_ID = "KEY_USER_ID"
    }
}
