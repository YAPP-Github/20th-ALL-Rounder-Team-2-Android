package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.repo.AppMetaRepository
import javax.inject.Inject

class ShownWelcomeUseCase @Inject constructor(
    private val appMetaRepository: AppMetaRepository,
) {

    suspend fun invoke(): Result<Unit> = appMetaRepository.shownWelcome()
}
