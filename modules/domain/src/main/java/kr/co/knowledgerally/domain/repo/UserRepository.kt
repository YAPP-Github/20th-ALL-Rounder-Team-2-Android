package kr.co.knowledgerally.domain.repo

import kotlinx.coroutines.flow.Flow
import kr.co.knowledgerally.domain.model.BallHistory
import kr.co.knowledgerally.domain.model.Notification
import kr.co.knowledgerally.domain.model.Onboard
import kr.co.knowledgerally.domain.model.User

interface UserRepository {

    suspend fun isOnboarded(): Result<Boolean>

    suspend fun submitOnboard(onboard: Onboard): Result<Unit>

    suspend fun modifyOnboard(onboard: Onboard): Result<Unit>

    fun getUserStream(): Flow<User>

    suspend fun refreshUser(): Result<User>

    suspend fun getBallHistories(): Result<List<BallHistory>>

    suspend fun getNotifications(): Result<List<Notification>>
}
