package kr.co.knowledgerally.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kr.co.knowledgerally.data.model.toData
import kr.co.knowledgerally.data.model.toDomain
import kr.co.knowledgerally.data.source.UserRemoteDataSource
import kr.co.knowledgerally.domain.model.BallHistory
import kr.co.knowledgerally.domain.model.Notification
import kr.co.knowledgerally.domain.model.Onboard
import kr.co.knowledgerally.domain.model.User
import kr.co.knowledgerally.domain.repo.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    private var user = MutableStateFlow<User?>(null)

    override suspend fun isOnboarded(): Result<Boolean> = userRemoteDataSource.isOnboarded()

    override suspend fun submitOnboard(onboard: Onboard): Result<Unit> =
        userRemoteDataSource.submitOnboard(onboard = onboard.toData())

    override suspend fun modifyOnboard(onboard: Onboard): Result<Unit> =
        userRemoteDataSource.modifyOnboard(onboard.toData())

    override fun getUserStream(): Flow<User> = user.filterNotNull()

    override suspend fun refreshUser(): Result<User> = userRemoteDataSource
        .getUser()
        .map { it.toDomain() }
        .onSuccess { user.value = it }

    override suspend fun getBallHistories(): Result<List<BallHistory>> =
        userRemoteDataSource.getBallHistories()
            .mapCatching { histories -> histories.map { it.toDomain() } }

    override suspend fun getNotifications(): Result<List<Notification>> =
        userRemoteDataSource.getNotifications()
            .mapCatching { notifications -> notifications.map { it.toDomain() } }
}
