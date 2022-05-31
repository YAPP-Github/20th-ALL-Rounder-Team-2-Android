package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Ball
import kr.co.knowledgerally.domain.repo.BallRepository
import javax.inject.Inject

class GetBallUseCase @Inject constructor(
    private val ballRepository: BallRepository
) {

    suspend operator fun invoke(): Result<Ball> = ballRepository.getBall()
}