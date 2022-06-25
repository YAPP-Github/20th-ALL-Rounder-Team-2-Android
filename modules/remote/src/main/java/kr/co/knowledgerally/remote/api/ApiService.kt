package kr.co.knowledgerally.remote.api

import kr.co.knowledgerally.remote.model.ProviderTokenRequest
import kr.co.knowledgerally.remote.model.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

internal interface ApiService {

    @POST("user/signup")
    suspend fun signUp(
        @Body providerToken: ProviderTokenRequest
    ): SignUpResponse

    @DELETE("user/me")
    suspend fun withdrawal()
}
