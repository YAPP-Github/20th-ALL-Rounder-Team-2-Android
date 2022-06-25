package kr.co.knowledgerally.remote.source

import kr.co.knowledgerally.data.source.UserRemoteDataSource
import kr.co.knowledgerally.remote.api.ApiService
import javax.inject.Inject

internal class UserRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : UserRemoteDataSource {

    override suspend fun isOnboarded(): Result<Boolean> = runCatching {
        apiService.isOnboarded().data.isOnboarded
    }
}