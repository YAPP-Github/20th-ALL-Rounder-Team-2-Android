package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.JwtToken
import kr.co.knowledgerally.domain.repo.AuthRepository
import javax.inject.Inject

class GetJwtTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(): Result<JwtToken> = authRepository.getJwtToken()
}
