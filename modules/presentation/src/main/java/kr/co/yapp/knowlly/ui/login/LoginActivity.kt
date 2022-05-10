package kr.co.yapp.knowlly.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kr.co.yapp.knowlly.ui.theme.KnowllyTheme

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KnowllyTheme {
                LoginScreen()
            }
        }
    }
}
