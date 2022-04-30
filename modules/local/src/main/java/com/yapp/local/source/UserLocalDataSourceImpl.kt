package com.yapp.local.source

import com.yapp.data.source.UserLocalDataSource
import javax.inject.Inject

internal class UserLocalDataSourceImpl @Inject constructor() : UserLocalDataSource {

    override suspend fun getName(): Result<String> {
        val random = (0..1).random()

        return if (random == 0) Result.success("Name from local")
        else Result.failure(IllegalArgumentException())
    }
}