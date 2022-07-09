package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Notification
import kr.co.knowledgerally.domain.repo.UserRepository
import javax.inject.Inject

class GetNotificationListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): Result<List<Notification>> = userRepository.getNotifications()
}
