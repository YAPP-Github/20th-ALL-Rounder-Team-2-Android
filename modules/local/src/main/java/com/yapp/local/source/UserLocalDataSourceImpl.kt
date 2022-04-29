package com.yapp.local.source

import com.yapp.data.source.UserLocalDataSource
import javax.inject.Inject

internal class UserLocalDataSourceImpl @Inject constructor() : UserLocalDataSource {

    // Local 에서는 항상 이름을 찾을 수 없음
    override suspend fun getName(): Result<String?> {
        return Result.success(null)
    }
}