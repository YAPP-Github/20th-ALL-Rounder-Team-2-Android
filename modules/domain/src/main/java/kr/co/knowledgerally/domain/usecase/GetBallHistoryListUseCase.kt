package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.BallHistory
import kr.co.knowledgerally.domain.repo.UserRepository
import javax.inject.Inject

class GetBallHistoryListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): Result<List<BallHistory>> = userRepository.getBallHistories()
}