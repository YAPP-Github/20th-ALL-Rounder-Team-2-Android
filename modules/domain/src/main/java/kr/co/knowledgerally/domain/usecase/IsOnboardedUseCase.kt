package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.repo.UserRepository
import javax.inject.Inject

class IsOnboardedUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): Result<Boolean> = userRepository.isOnboarded()
}