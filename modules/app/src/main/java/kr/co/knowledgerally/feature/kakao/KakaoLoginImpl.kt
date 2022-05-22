package kr.co.knowledgerally.feature.kakao

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal class KakaoLoginImpl @Inject constructor() : KakaoLogin {

    /**
     * @param context: Activity context
     */
    override suspend fun login(context: Context): Result<KakaoAccessToken> = runCatching {
        suspendCancellableCoroutine { continuation ->
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, throwable ->
                when {
                    throwable != null -> continuation.resumeWithException(throwable)
                    token != null && continuation.isActive -> {
                        val accessToken = KakaoAccessToken(token.accessToken)
                        continuation.resume(accessToken)
                    }
                }
            }

            val userApiClient = UserApiClient.instance
            if (userApiClient.isKakaoTalkLoginAvailable(context)) {
                userApiClient.loginWithKakaoTalk(context, callback = callback)
            } else {
                userApiClient.loginWithKakaoAccount(context, callback = callback)
            }
        }
    }
}
