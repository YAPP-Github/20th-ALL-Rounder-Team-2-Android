package kr.co.knowledgerally.remote.api

import kr.co.knowledgerally.remote.model.BallHistoryResponseWrapper
import kr.co.knowledgerally.remote.model.CoachLectureResponseWrapper
import kr.co.knowledgerally.remote.model.OnboardRequest
import kr.co.knowledgerally.remote.model.OnboardedResponse
import kr.co.knowledgerally.remote.model.PlayerLectureResponseWrapper
import kr.co.knowledgerally.remote.model.ProviderTokenRequest
import kr.co.knowledgerally.remote.model.SignInResponse
import kr.co.knowledgerally.remote.model.SignUpResponse
import kr.co.knowledgerally.remote.model.UserExistsResponse
import kr.co.knowledgerally.remote.model.UserResponseWrapper
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
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
    ): SignUpResponse

    @POST("user/signin")
    suspend fun signIn(
        @Body providerToken: ProviderTokenRequest
    ): SignInResponse

    @DELETE("user/me")
    suspend fun withdrawal()

    @GET("user/me")
    suspend fun getUser(): UserResponseWrapper

    @GET("user/check-onboard")
    suspend fun isOnboarded(): OnboardedResponse

    @POST("user/image")
    @Multipart
    suspend fun uploadUserImage(@Part part: MultipartBody.Part)

    @PATCH("user/onboard")
    suspend fun submitOnboard(@Body onboard: OnboardRequest)

    @FormUrlEncoded
    @PATCH("user/setting/push")
    suspend fun updatePushActive(@Field("pushActive") active: Boolean)

    @GET("ballhistory/me")
    suspend fun getBallHistories(): BallHistoryResponseWrapper

    @GET("user/lecture/me")
    suspend fun getPlayerLectures(): PlayerLectureResponseWrapper

    @GET("coach/lecture/me")
    suspend fun getCoachLectures(): CoachLectureResponseWrapper
}
