package kr.co.knowledgerally.ui.login

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kr.co.knowledgerally.model.SocialUserInfo

internal class LoginSdk(private val context: Context) {

    fun kakaoLogin(): Result<SocialUserInfo?> {
        var result: Result<SocialUserInfo?> = Result.success(null)

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                result = Result.failure(error)
            } else if (token != null) {
                result = Result.success(SocialUserInfo(token.accessToken))
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {

                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    result = Result.success(SocialUserInfo(token.accessToken))
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }

        return result
    }

    fun googleLogin(): Result<SocialUserInfo?> = Result.success(null)
}
