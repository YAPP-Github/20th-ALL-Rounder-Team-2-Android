package kr.co.knowledgerally.ui.notification

import kr.co.knowledgerally.ui.model.NotificationModel

sealed class NotificationUiState {
    object Loading : NotificationUiState()
    data class Success(val list: List<NotificationModel>) : NotificationUiState()
    object Empty : NotificationUiState()
    object Failure : NotificationUiState()
}
