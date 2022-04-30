package com.yapp.data.repo

import com.yapp.data.source.UserLocalDataSource
import com.yapp.data.source.UserRemoteDataSource
import com.yapp.domain.repo.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getName(): Result<String> = runCatching {
        val name = userLocalDataSource.getName().getOrThrow()

        return if (name == null) {
            userRemoteDataSource.getName()
        } else {
            Result.success(name)
        }
    }
}
