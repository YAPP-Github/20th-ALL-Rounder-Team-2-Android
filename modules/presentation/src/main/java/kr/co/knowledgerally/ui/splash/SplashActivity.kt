package kr.co.knowledgerally.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
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

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            KnowllyTheme {
                val uiState by viewModel.uiState.collectAsState()
                when (uiState) {
                    SplashUiState.AlreadyLoggedIn -> startMainActivity()
                    SplashUiState.NeedToOnboard -> startProfileActivity()
                    SplashUiState.Loading -> Unit // no-op
                    SplashUiState.Tutorial -> Unit // no-op
                }
                Splash(visible = uiState != SplashUiState.Tutorial)
                SplashPage(
                    visible = uiState == SplashUiState.Tutorial,
                    items = PageItems,
                    startKnowlly = { startLoginActivity() },
                )
            }
        }
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
        fun startActivity(context: Context) {
            val intent = Intent(context, SplashActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }
    }
}
