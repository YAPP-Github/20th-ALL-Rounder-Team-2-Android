package kr.co.knowledgerally.data.source

import kr.co.knowledgerally.data.model.OnboardEntity
import kr.co.knowledgerally.data.model.UserEntity

interface UserRemoteDataSource {

    suspend fun isOnboarded(): Result<Boolean>

    suspend fun submitOnboard(request: OnboardEntity): Result<Unit>

    suspend fun getUser(): Result<UserEntity>
}
