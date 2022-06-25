package kr.co.knowledgerally.remote.api

import kr.co.knowledgerally.remote.model.JwtTokenResponseWrapper
import kr.co.knowledgerally.remote.model.OnboardedResponse
import kr.co.knowledgerally.remote.model.ProviderTokenRequest
import kr.co.knowledgerally.remote.model.UserExistsResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface ApiService {

    @GET("user/user-exists")
    suspend fun isSignedUp(
        @Query("provider_name") providerName: String,
        @Query("provider_token") providerToken: String
    ): UserExistsResponse

    @POST("user/signup")
    suspend fun signUp(
        @Body providerToken: ProviderTokenRequest
    ): JwtTokenResponseWrapper

    @POST("user/signin")
    suspend fun signIn(
        @Body providerToken: ProviderTokenRequest
    ): JwtTokenResponseWrapper

    @DELETE("user/me")
    suspend fun withdrawal()

    @GET("user/user-onboarded") // 임시 endpoint, 확정 후 수정 필요
    suspend fun isOnboarded(): OnboardedResponse
}
