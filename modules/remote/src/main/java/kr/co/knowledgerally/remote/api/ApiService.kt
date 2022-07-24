package kr.co.knowledgerally.remote.api

import kr.co.knowledgerally.remote.model.BallHistoryResponseWrapper
import kr.co.knowledgerally.remote.model.CategoryResponseWrapper
import kr.co.knowledgerally.remote.model.CoachLectureResponseWrapper
import kr.co.knowledgerally.remote.model.ImagesResponseWrapper
import kr.co.knowledgerally.remote.model.LectureInfoResponseWrapper
import kr.co.knowledgerally.remote.model.LectureStateResponse
import kr.co.knowledgerally.remote.model.NotificationResponseWrapper
import kr.co.knowledgerally.remote.model.OnboardRequest
import kr.co.knowledgerally.remote.model.OnboardedResponse
import kr.co.knowledgerally.remote.model.PlayerLectureResponseWrapper
import kr.co.knowledgerally.remote.model.ProviderTokenRequest
import kr.co.knowledgerally.remote.model.RegistrationRequest
import kr.co.knowledgerally.remote.model.SchedulesRequest
import kr.co.knowledgerally.remote.model.SignInResponse
import kr.co.knowledgerally.remote.model.SignUpResponse
import kr.co.knowledgerally.remote.model.UserExistsResponse
import kr.co.knowledgerally.remote.model.UserResponseWrapper
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
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

    @PATCH("user/me")
    suspend fun patchUser(@Body onboard: OnboardRequest): UserResponseWrapper

    @GET("user/me")
    suspend fun getUser(): UserResponseWrapper

    @GET("user/check-onboard")
    suspend fun isOnboarded(): OnboardedResponse

    @POST("user/image")
    @Multipart
    suspend fun uploadUserImage(@Part part: MultipartBody.Part)

    @DELETE("user/image")
    suspend fun clearUserImage()

    @PATCH("user/onboard")
    suspend fun submitOnboard(@Body onboard: OnboardRequest)

    @GET("ballhistory/me")
    suspend fun getBallHistories(): BallHistoryResponseWrapper

    @GET("notification/me")
    suspend fun getNotifications(): NotificationResponseWrapper

    @GET("user/lecture/me")
    suspend fun getPlayerLectureInfoList(@Query("state") state: LectureStateResponse? = null): PlayerLectureResponseWrapper

    @GET("coach/lecture/me")
    suspend fun getCoachLectureInfoList(@Query("state") state: LectureStateResponse? = null): CoachLectureResponseWrapper

    @GET("category")
    suspend fun getCategoryList(): CategoryResponseWrapper

    @POST("lectureinfo/images")
    @Multipart
    suspend fun uploadLectureImages(
        @Part parts: List<MultipartBody.Part>
    ): ImagesResponseWrapper

    @POST("lectureinfo")
    suspend fun uploadLectureInfo(
        @Body registration: RegistrationRequest,
    ): LectureInfoResponseWrapper

    @POST("lecture-schedule/lectureinfo/{lectureId}")
    suspend fun uploadLectureSchedules(
        @Path("lectureId") lectureId: Long,
        @Body request: SchedulesRequest,
    )
}
