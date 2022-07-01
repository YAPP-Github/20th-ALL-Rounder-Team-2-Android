package kr.co.knowledgerally.remote.api

import kotlinx.coroutines.runBlocking
import kr.co.knowledgerally.data.provider.AccessTokenProvider
import kr.co.knowledgerally.data.provider.RefreshTokenProvider
import kr.co.knowledgerally.remote.model.RefreshResponse
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

internal class Authenticator constructor(
    private val apiService: RefreshApiService,
    private val accessTokenProvider: AccessTokenProvider,
    private val refreshTokenProvider: RefreshTokenProvider,
    private val authenticationListener: AuthenticationListener
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = refreshTokenProvider.value
        if (refreshToken.isBlank()) {
            authenticationListener.onSessionExpired()
            return null
        }

        return refresh(refreshToken).fold(
            onSuccess = {
                accessTokenProvider.value = it.data.accessToken
                refreshTokenProvider.value = it.data.refreshToken
                response.request
            },
            onFailure = {
                authenticationListener.onSessionExpired()
                null
            }
        )
    }

    private fun refresh(refreshToken: String): Result<RefreshResponse> = runBlocking {
        runCatching {
            apiService.refresh(refreshToken)
        }
    }
}
