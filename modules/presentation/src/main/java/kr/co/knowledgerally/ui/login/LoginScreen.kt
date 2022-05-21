package kr.co.knowledgerally.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.model.LoginTypeModel
import kr.co.knowledgerally.ui.component.KnowllyFilledButton
import kr.co.knowledgerally.ui.component.KnowllyOutlinedButton

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    LoginScreen(onLogin = viewModel::login)
}

@Composable
fun LoginScreen(onLogin: (LoginTypeModel) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        KnowllyFilledButton(
            text = "구글 로그인",
            onClick = { onLogin(LoginTypeModel.Google) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        KnowllyFilledButton(
            text = "구글 로그인",
            onClick = { onLogin(LoginTypeModel.Google) },
            enabled = false
        )
        Spacer(modifier = Modifier.height(8.dp))
        KnowllyOutlinedButton(
            text = "카카오 로그인",
            onClick = { onLogin(LoginTypeModel.Kakao) }
        )
    }
}
