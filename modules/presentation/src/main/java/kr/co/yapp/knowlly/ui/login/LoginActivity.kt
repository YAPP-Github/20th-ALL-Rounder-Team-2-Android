package kr.co.yapp.knowlly.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.co.yapp.knowlly.ui.MainActivity
import kr.co.yapp.knowlly.ui.login.compose.LoginScreen
import kr.co.yapp.knowlly.ui.theme.KnowllyTheme

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent()
        observeViewModel()
    }

    private fun setContent() = setContent {
        KnowllyTheme {
            LoginScreen(viewModel)
        }
    }

    private fun observeViewModel() {
        viewModel.isLoggedIn
            .filter { it }
            .onEach { startMainActivity() }
            .launchIn(lifecycleScope)
    }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
}
