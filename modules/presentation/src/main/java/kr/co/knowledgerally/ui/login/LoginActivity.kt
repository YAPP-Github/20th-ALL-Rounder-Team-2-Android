package kr.co.knowledgerally.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.main.MainActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent()
        observeViewModel()
    }

    private fun setContent() = setContent {
        KnowllyTheme {
            LoginScreen(viewModel = viewModel)
        }
    }

    private fun observeViewModel() {
        viewModel.state
            .filter { it is LoginState.Success }
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
