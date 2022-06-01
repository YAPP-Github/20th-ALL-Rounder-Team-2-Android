package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.domain.model.BallCount
import kr.co.knowledgerally.domain.model.BallHistory
import kr.co.knowledgerally.domain.repo.BallRepository
import javax.inject.Inject

internal class BallRepositoryImpl @Inject constructor() : BallRepository {

    override suspend fun getBall(): Result<BallCount> = runCatching { BallCount(1) }

    override suspend fun getBallHistories(): Result<List<BallHistory>> =
        runCatching { emptyList() }
}