package kr.co.knowledgerally.bus

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object EventBus {
    private val sharedFlow = MutableSharedFlow<Event>()
    val event = sharedFlow.asSharedFlow()

    suspend fun emit(event: Event) {
        sharedFlow.emit(event)
    }
}
