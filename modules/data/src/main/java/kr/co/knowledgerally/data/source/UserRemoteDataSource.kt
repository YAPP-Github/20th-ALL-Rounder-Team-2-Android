package kr.co.knowledgerally.data.source

import kr.co.knowledgerally.data.model.BallHistoryEntity
import kr.co.knowledgerally.data.model.OnboardEntity
import kr.co.knowledgerally.data.model.NotificationEntity
import kr.co.knowledgerally.data.model.UserEntity

interface UserRemoteDataSource {

    suspend fun isOnboarded(): Result<Boolean>

    suspend fun submitOnboard(onboard: OnboardEntity): Result<Unit>

    suspend fun modifyOnboard(onboard: OnboardEntity): Result<Unit>

    suspend fun getUser(): Result<UserEntity>

    suspend fun getBallHistories(): Result<List<BallHistoryEntity>>

    suspend fun getNotifications(): Result<List<NotificationEntity>>
}
