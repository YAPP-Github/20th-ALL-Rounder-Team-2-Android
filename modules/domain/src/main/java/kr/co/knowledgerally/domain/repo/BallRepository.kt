package kr.co.knowledgerally.domain.repo

import kr.co.knowledgerally.domain.model.Ball
import kr.co.knowledgerally.domain.model.BallHistory

interface BallRepository {

    suspend fun getBall(): Result<Ball>

    suspend fun getBallHistoryList(): Result<List<BallHistory>>
}