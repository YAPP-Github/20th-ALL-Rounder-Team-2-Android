package kr.co.yapp.knowlly.ui.login

import androidx.compose.ui.graphics.Color

sealed class LoginPlatform {
    data class LoginButtonStyle(
        val text: String,
        val textColor: Color
    )

    enum class Type { GOOGLE, KAKAO }

    companion object {
        val loginButtonStyle = listOf(
            Type.GOOGLE to LoginButtonStyle(text = "구글 로그인", textColor = Color.White),
            Type.KAKAO to LoginButtonStyle(text = "카카오 로그인", textColor = Color.White)
        )
    }
}
