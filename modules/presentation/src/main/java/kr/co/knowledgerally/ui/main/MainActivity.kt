package kr.co.knowledgerally.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.bridge.WebAppInterface
import kr.co.knowledgerally.ui.ball.BallActivity
import kr.co.knowledgerally.ui.notification.NotificationActivity
import kr.co.knowledgerally.ui.register.RegisterActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var webAppInterface: WebAppInterface

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                MainScreen(
                    viewModel = viewModel,
                    navigateToRegister = ::startRegisterActivity,
                    navigateToBall = ::startBallActivity,
                    navigateToNotification = ::startNotificationActivity,
                    webAppInterface = webAppInterface
                )
            }
        }
    }

    private fun startRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun startBallActivity() {
        val intent = Intent(this, BallActivity::class.java)
        startActivity(intent)
    }

    private fun startNotificationActivity() {
        val intent = Intent(this, NotificationActivity::class.java)
        startActivity(intent)
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
