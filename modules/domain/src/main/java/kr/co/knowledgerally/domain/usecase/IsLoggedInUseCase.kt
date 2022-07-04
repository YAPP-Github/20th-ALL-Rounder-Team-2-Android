package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.repo.AuthRepository
import javax.inject.Inject

class IsLoggedInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(): Result<Boolean> = authRepository
        .getJwtToken()
        .map { jwtToken -> jwtToken.isNotEmpty }
}
