package kr.co.knowledgerally.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.co.knowledgerally.domain.model.User
import kr.co.knowledgerally.domain.repo.UserRepository
import javax.inject.Inject

class GetUserStreamUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    operator fun invoke(): Flow<User> = userRepository.getUserStream()
}
