package kr.co.knowledgerally.remote.api

import kr.co.knowledgerally.remote.model.RefreshResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RefreshApiService {

    @GET("user/refresh")
    suspend fun refresh(
        @Query("refresh_token") refreshToken: String,
    ): RefreshResponse
}
