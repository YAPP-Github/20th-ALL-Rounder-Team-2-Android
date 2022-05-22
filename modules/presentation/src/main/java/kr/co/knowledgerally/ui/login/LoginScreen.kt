package kr.co.knowledgerally.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kr.co.knowledgerally.model.KakaoProfile
import kr.co.knowledgerally.ui.component.KnowllyOutlinedButton

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    LoginScreen(
        onLogin = viewModel::login,
        onFailToLogin = viewModel::failToLogin
    )
}

@Composable
fun LoginScreen(
    onLogin: (KakaoProfile?) -> Unit,
    onFailToLogin: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current

        KnowllyOutlinedButton(
            text = "카카오 로그인",
            onClick = {
                KakaoSdk.kakaoLogin(context)
                    .onSuccess { onLogin(it) }
                    .onFailure { onFailToLogin() }
            }
        )
    }
}
