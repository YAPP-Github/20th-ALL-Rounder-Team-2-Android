package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Onboard
import kr.co.knowledgerally.domain.repo.UserRepository
import javax.inject.Inject

class ModifyOnboardUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(request: Onboard): Result<Unit> =
        userRepository.submitOnboard(request = request, isModified = true)
}
