package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.repo.AppMetaRepository
import javax.inject.Inject

class IsWelcomeShownUseCase @Inject constructor(
    private val appMetaRepository: AppMetaRepository
) {

    suspend operator fun invoke(): Result<Boolean> = appMetaRepository.isWelcomeShown()
}
