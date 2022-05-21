package kr.co.knowledgerally.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kr.co.knowledgerally.model.LoginTypeModel

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
        Button(
            onClick = { onLogin(LoginTypeModel.Google) }
        ) {
            Text("구글 로그인")
        }
        Button(
            onClick = { onLogin(LoginTypeModel.Kakao) }
        ) {
            Text("카카오 로그인")
        }
    }
}