package kr.co.knowledgerally.domain.repo

import kotlinx.coroutines.flow.Flow
import kr.co.knowledgerally.domain.model.Onboard
import kr.co.knowledgerally.domain.model.User

interface UserRepository {

    suspend fun isOnboarded(): Result<Boolean>

    suspend fun submitOnboard(request: Onboard): Result<Unit>

    fun getUserStream(): Flow<User>

    suspend fun refreshUser(): Result<User>

    suspend fun updatePushActive(active: Boolean): Result<Unit>
}
