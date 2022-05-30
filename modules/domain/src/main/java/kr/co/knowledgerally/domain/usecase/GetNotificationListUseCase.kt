package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Notification
import javax.inject.Inject

class GetNotificationListUseCase @Inject constructor() {

    suspend operator fun invoke(): Result<List<Notification>> = runCatching { emptyList() }
}