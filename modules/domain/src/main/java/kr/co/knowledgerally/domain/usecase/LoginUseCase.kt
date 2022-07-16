package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.LoginResult
import kr.co.knowledgerally.domain.model.ProviderToken
import kr.co.knowledgerally.domain.repo.AuthRepository
import kr.co.knowledgerally.domain.repo.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(
        providerToken: ProviderToken
    ): Result<LoginResult> = runCatching {
        val isSignedUp = authRepository.isSignedUp(providerToken).getOrThrow()
        if (!isSignedUp) return@runCatching LoginResult.NotSigned(providerToken)

        authRepository.signIn(providerToken).getOrThrow()
        val isOnboarded = userRepository.isOnboarded().getOrThrow()
        if (isOnboarded) {
            LoginResult.Signed
        } else {
            LoginResult.Onboard
        }
    }
}
