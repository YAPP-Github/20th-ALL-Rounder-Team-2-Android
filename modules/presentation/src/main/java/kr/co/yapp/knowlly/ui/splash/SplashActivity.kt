package kr.co.yapp.knowlly.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kr.co.yapp.knowlly.ui.MainActivity
import kr.co.yapp.knowlly.ui.login.LoginActivity

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoggedIn = viewModel.isLoggedInState.value

        lifecycleScope.launchWhenCreated {
            delay(1500)

            val nextActivity = when (isLoggedIn) {
                LoggedInState.AlreadyLoggedIn -> MainActivity::class.java
                LoggedInState.NeedToLogin -> LoginActivity::class.java
            }

            val intent = Intent(this@SplashActivity, nextActivity)
            startActivity(intent)
            finish()
        }
    }
}
