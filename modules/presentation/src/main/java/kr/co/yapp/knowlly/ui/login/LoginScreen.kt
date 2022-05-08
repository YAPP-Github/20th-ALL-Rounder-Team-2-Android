package kr.co.yapp.knowlly.ui.login

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material3.Text
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
fun LoginScreen(onLogin: (LoginPlatform.Type) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        LoginButton(onClick = onLogin)
    }
}

@Composable
fun LoginButton(onClick: (LoginPlatform.Type) -> Unit) {
    val context = LocalContext.current

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(LoginPlatform.loginButtonStyle) {
            Button(onClick = {
                onClick(it.first)

                val intent = Intent(context, MainActivity::class.java)
                TaskStackBuilder
                    .create(context)
                    .addNextIntentWithParentStack(intent)
                    .startActivities()
            }) {
                Text(text = it.second.text, color = it.second.textColor)
            }
        }
    }
}
