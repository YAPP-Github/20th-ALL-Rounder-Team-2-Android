package kr.co.knowledgerally.ui.notification

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.usecase.GetNotificationUseCase
import kr.co.knowledgerally.ui.model.toPresentation
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotificationUseCase: GetNotificationUseCase
) : BaseViewModel() {

    var state by mutableStateOf<NotificationUiState>(NotificationUiState.Loading)
        private set

    init {
        fetchNotificationList()
    }

    private fun fetchNotificationList() {
        state = NotificationUiState.Loading
        viewModelScope.launch {
            val result = getNotificationUseCase()
            result
                .mapCatching {
                    it.map { notification -> notification.toPresentation() }
                }
                .onSuccess {
                    state =
                        if (it.isNotEmpty()) NotificationUiState.Success(it)
                        else NotificationUiState.Empty
                }
                .onFailure { state = NotificationUiState.Failure }
        }
    }
}
