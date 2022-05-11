package kr.co.yapp.knowlly.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.yapp.knowlly.ui.MainActivity
import kr.co.yapp.knowlly.ui.base.BaseActivity
import kr.co.yapp.knowlly.ui.login.LoginActivity

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(SPLASH_TIME_MILLIS)
            viewModel.state.collect { state -> handleState(state) }
        }
    }

    private fun handleState(state: SplashUiState) = when (state) {
        SplashUiState.AlreadyLoggedIn -> startMainActivity()
        SplashUiState.NeedToLogin -> startLoginActivity()
        SplashUiState.Unspecified -> Unit /* no-op */
    }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    private fun startLoginActivity() {
        LoginActivity.startActivity(this)
        finish()
    }

    companion object {
        private const val SPLASH_TIME_MILLIS = 1_500L
    }
}
