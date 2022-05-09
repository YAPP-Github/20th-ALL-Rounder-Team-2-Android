package kr.co.yapp.knowlly.ui.login

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.TaskStackBuilder
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.co.yapp.knowlly.ui.MainActivity

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    LoginScreen(onLogin = viewModel::login)
}

@Composable
fun LoginScreen(onLogin: (LoginType) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current

        Button(
            onClick = {
                onLogin(LoginType.GOOGLE)
                moveToMainActivity(context)
            }
        ) {
            Text("구글 로그인")
        }
        Button(
            onClick = {
                onLogin(LoginType.KAKAO)
                moveToMainActivity(context)
            }
        ) {
            Text("카카오 로그인")
        }
    }
}

fun moveToMainActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    TaskStackBuilder
        .create(context)
        .addNextIntentWithParentStack(intent)
        .startActivities()
}
