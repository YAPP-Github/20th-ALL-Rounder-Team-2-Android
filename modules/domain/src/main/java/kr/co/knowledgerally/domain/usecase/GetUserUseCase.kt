package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.User
import kr.co.knowledgerally.domain.repo.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(): Result<User> = userRepository.getUser()
}
