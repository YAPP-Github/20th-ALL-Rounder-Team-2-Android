package kr.co.yapp.knowlly.domain.usecase

import kr.co.yapp.knowlly.domain.repo.AuthRepository
import javax.inject.Inject

class IsLoggedInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(): Result<Boolean> = authRepository
        .getAccessToken()
        .map { it.isNotBlank() }
}
