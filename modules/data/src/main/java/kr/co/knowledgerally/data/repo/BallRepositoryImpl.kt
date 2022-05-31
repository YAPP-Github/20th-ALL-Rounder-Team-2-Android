package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.domain.model.Ball
import kr.co.knowledgerally.domain.model.BallHistory
import kr.co.knowledgerally.domain.repo.BallRepository
import javax.inject.Inject

internal class BallRepositoryImpl @Inject constructor() : BallRepository {

    override suspend fun getBall(): Result<Ball> = runCatching { Ball(1) }

    override suspend fun getBallHistoryList(): Result<List<BallHistory>> =
        runCatching { emptyList() }
}