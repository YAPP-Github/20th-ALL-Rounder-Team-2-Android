package kr.co.knowledgerally.ui.notification

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.usecase.GetNotificationListUseCase
import kr.co.knowledgerally.ui.model.toPresentation
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotificationListUseCase: GetNotificationListUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow<NotificationUiState>(NotificationUiState.Loading)
    val state: StateFlow<NotificationUiState> = _state.asStateFlow()

    init {
        fetchNotificationList()
    }

    private fun fetchNotificationList() {
        _state.value = NotificationUiState.Loading
        launch {
            val result = getNotificationListUseCase()
            result
                .mapCatching {
                    it.map { notification -> notification.toPresentation() }
                }
                .onSuccess {
                    _state.value =
                        if (it.isNotEmpty()) {
                            NotificationUiState.Success(it)
                        } else {
                            NotificationUiState.Empty
                        }
                }
                .onFailure { _state.value = NotificationUiState.Failure }
        }
    }
}
