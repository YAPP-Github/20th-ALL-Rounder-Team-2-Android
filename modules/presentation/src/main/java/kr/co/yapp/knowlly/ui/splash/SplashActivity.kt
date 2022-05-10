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

        lifecycleScope.launchWhenCreated {
            delay(1500)

            viewModel.isLoggedInState.collect {
                val nextActivity = when (it) {
                    SplashUiState.AlreadyLoggedIn -> MainActivity::class.java
                    SplashUiState.NeedToLogin -> LoginActivity::class.java
                    SplashUiState.Checking -> null
                }
                if (nextActivity != null) {
                    val intent = Intent(this@SplashActivity, nextActivity)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
