package kr.co.knowledgerally.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.login.LoginActivity
import kr.co.knowledgerally.ui.main.MainActivity
import kr.co.knowledgerally.ui.profile.ProfileActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ProfileActivity.startActivity(this)
        return
        setContent()

        lifecycleScope.launch {
            delay(SPLASH_TIME_MILLIS)
            viewModel.state.collect { state -> handleState(state) }
        }
    }

    private fun setContent() = setContent {
        val systemUiController: SystemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(KnowllyTheme.colors.primaryDark)

        KnowllyTheme {
            SplashScreen()
        }
    }

    private fun handleState(state: SplashUiState) = when (state) {
        SplashUiState.AlreadyLoggedIn -> startMainActivity()
        SplashUiState.NeedToOnboard -> startProfileActivity()
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

    private fun startProfileActivity() {
        ProfileActivity.startActivity(this)
        finish()
    }

    companion object {
        private const val SPLASH_TIME_MILLIS = 2_000L

        fun startActivity(context: Context) {
            val intent = Intent(context, SplashActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }
    }
}
