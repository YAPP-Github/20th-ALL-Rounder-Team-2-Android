package com.yapp.data.repo

import com.yapp.data.source.UserLocalDataSource
import com.yapp.data.source.UserRemoteDataSource
import com.yapp.domain.repo.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    // Remote 에서는 항상 이름을 가져올 수 있다고 가정
    override suspend fun getName(): String {
        return getNameFromLocal() ?: getNameFromRemote()
    }

    private suspend fun getNameFromLocal(): String? {
        return userLocalDataSource.getName().getOrThrow()
    }

    private suspend fun getNameFromRemote(): String {
        return userRemoteDataSource.getName().getOrThrow()
    }
}
