package com.yapp.remote.source

import com.yapp.data.source.UserRemoteDataSource
import javax.inject.Inject

internal class UserRemoteDataSourceImpl @Inject constructor() : UserRemoteDataSource {
    
    override suspend fun getName(): Result<String> {
        return Result.success("Android")
    }
}