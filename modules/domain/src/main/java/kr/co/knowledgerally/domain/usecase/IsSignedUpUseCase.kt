package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.ProviderToken
import kr.co.knowledgerally.domain.repo.AuthRepository
import javax.inject.Inject

class IsSignedUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(providerToken: ProviderToken): Result<Boolean> =
        authRepository.isSignedUp(providerToken)
}