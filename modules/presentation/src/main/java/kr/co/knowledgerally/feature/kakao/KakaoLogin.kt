package kr.co.knowledgerally.feature.kakao

import android.content.Context

interface KakaoLogin {

    suspend fun login(context: Context): Result<KakaoAccessToken>
}
