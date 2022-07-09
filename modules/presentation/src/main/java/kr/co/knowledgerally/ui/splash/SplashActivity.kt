package kr.co.knowledgerally.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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

        setContent {
            val systemUiController: SystemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(KnowllyTheme.colors.primaryDark)

            KnowllyTheme {
                val uiState by viewModel.uiState.collectAsState()
                when (uiState) {
                    SplashUiState.AlreadyLoggedIn -> startMainActivity()
                    SplashUiState.NeedToOnboard -> startProfileActivity()
                    SplashUiState.NeedToLogin -> startLoginActivity()
                    SplashUiState.Tutorial -> SplashPage(
                        items = PageItems,
                        startKnowlly = { startLoginActivity() },
                    )
                    SplashUiState.Loading -> Splash()
                }
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
