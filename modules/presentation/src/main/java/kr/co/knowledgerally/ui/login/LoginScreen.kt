package kr.co.knowledgerally.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kr.co.knowledgerally.model.LoginTypeModel
import kr.co.knowledgerally.model.SocialUserInfo
import kr.co.knowledgerally.ui.component.KnowllyOutlinedButton

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onSocialLogin: (LoginTypeModel) -> Result<SocialUserInfo?>,
) {
    LoginScreen(
        onServiceLogin = viewModel::login,
        onSocialLogin = onSocialLogin
    )
}

@Composable
fun LoginScreen(
    onServiceLogin: (SocialUserInfo?) -> Unit,
    onSocialLogin: (LoginTypeModel) -> Result<SocialUserInfo?>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        KnowllyOutlinedButton(
            text = "카카오 로그인",
            onClick = {
                onSocialLogin(LoginTypeModel.Kakao)
                    .onSuccess { onServiceLogin(it) }
                    .onFailure { /* 카카오에 로그인할 수 없습니다 */ }
            }
        )
    }
}
