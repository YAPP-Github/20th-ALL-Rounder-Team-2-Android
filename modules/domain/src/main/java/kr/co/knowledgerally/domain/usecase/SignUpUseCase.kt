package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.JwtToken
import kr.co.knowledgerally.domain.model.ProviderToken
import kr.co.knowledgerally.domain.repo.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(providerToken: ProviderToken): Result<JwtToken> =
        authRepository.signUp(providerToken)
}
