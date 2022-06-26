package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.ProviderToken
import kr.co.knowledgerally.domain.repo.AuthRepository
import kr.co.knowledgerally.domain.repo.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(
        providerToken: ProviderToken,
        pushActive: Boolean
    ): Result<Unit> = authRepository
        .signUp(providerToken)
        .mapCatching { userRepository.updatePushActive(pushActive).getOrThrow() }
}
