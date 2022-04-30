package com.yapp.remote.source

import com.yapp.data.source.UserRemoteDataSource
import javax.inject.Inject

internal class UserRemoteDataSourceImpl @Inject constructor() : UserRemoteDataSource {

    override suspend fun getName(): Result<String> {
        val random = (0..1).random()

        return if (random == 0) {
            Result.success("Name from remote")
        } else {
            Result.failure(IllegalArgumentException())
        }
    }
}