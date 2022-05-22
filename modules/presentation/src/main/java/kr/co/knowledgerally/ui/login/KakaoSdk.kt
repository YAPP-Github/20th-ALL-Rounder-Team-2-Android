package kr.co.knowledgerally.ui.login

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kr.co.knowledgerally.model.KakaoProfile

object KakaoSdk {

    fun kakaoLogin(context: Context): Result<KakaoProfile?> {
        var result: Result<KakaoProfile?> = Result.success(null)

        val kakaoAccountLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                result = Result.failure(error)
            } else if (token != null) {
                result = getKakaoProfile()
                    .onSuccess { Result.success(it) }
                    .onFailure { Result.failure<KakaoProfile?>(it) }
            }
        }

        val kakaoTalkLoginCallback: (OAuthToken?, Throwable?) -> Unit = callback@{ token, error ->
            if (error != null) {

                // 의도적인 로그인 취소 (예: 뒤로가기)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@callback
                }

                // 카카오계정으로 재시도
                kakaoAccountLogin(context, kakaoAccountLoginCallback)
            } else if (token != null) {
                result = getKakaoProfile()
                    .onSuccess { Result.success(it) }
                    .onFailure { Result.failure<KakaoProfile?>(it) }
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            kakaoTalkLogin(context, kakaoTalkLoginCallback)
        } else {
            kakaoAccountLogin(context, kakaoAccountLoginCallback)
        }

        return result // FIXME: UI에서 항상 초깃값인 Result.Success(null)을 반환하는 버그
    }

    private fun kakaoTalkLogin(context: Context, callback: (OAuthToken?, Throwable?) -> Unit) {
        UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
    }

    private fun kakaoAccountLogin(context: Context, callback: (OAuthToken?, Throwable?) -> Unit) {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
    }

    fun getKakaoProfile(): Result<KakaoProfile?> {
        var result: Result<KakaoProfile?> = Result.success(null)

        UserApiClient.instance.me { user, error ->
            if (error != null) {
                result = Result.failure(error)
            } else if (user != null) {
                result =
                    Result.success(KakaoProfile(nickname = user.kakaoAccount?.profile?.nickname!!))
            }
        }

        return result
    }
}
