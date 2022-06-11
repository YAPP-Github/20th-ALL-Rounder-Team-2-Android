package kr.co.knowledgerally.domain.repo

import kr.co.knowledgerally.domain.model.BallCount
import kr.co.knowledgerally.domain.model.BallHistory

interface BallRepository {

    suspend fun getBall(): Result<BallCount>

    suspend fun getBallHistories(): Result<List<BallHistory>>
}