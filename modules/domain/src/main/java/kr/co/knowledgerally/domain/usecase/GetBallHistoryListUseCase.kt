package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.BallHistory
import kr.co.knowledgerally.domain.repo.BallRepository
import javax.inject.Inject

class GetBallHistoryListUseCase @Inject constructor(
    private val ballRepository: BallRepository
) {

    suspend operator fun invoke(): Result<List<BallHistory>> = ballRepository.getBallHistories()
}