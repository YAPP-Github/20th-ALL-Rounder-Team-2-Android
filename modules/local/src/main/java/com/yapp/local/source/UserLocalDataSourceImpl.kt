package com.yapp.local.source

import com.yapp.data.source.UserLocalDataSource
import javax.inject.Inject

internal class UserLocalDataSourceImpl @Inject constructor() : UserLocalDataSource {

    override suspend fun getName(): Result<String?> {
        return when ((0..2).random()) {
            0 -> Result.success("Name from local")
            1 -> Result.success(null)
            else -> Result.failure(IllegalArgumentException())
        }
    }
}