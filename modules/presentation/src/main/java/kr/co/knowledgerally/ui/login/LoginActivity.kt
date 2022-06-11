package kr.co.knowledgerally.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.feature.kakao.KakaoLogin
import kr.co.knowledgerally.ui.main.MainActivity
import kr.co.knowledgerally.ui.policy.PolicyActivity
import kr.co.knowledgerally.ui.signup.SignUpActivity
import kr.co.knowledgerally.ui.terms.TermsActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    @Inject
    lateinit var kakaoLogin: KakaoLogin

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent()
        observeViewModel()
    }

    private fun setContent() = setContent {
        val systemUiController: SystemUiController = rememberSystemUiController()
        systemUiController.setStatusBarColor(KnowllyTheme.colors.primaryLight)

        KnowllyTheme {
            LoginScreen(
                onLogin = { requestKakaoLogin() },
                navigateToTerms = { startTermsActivity() },
                navigateToPolicy = { startPolicyActivity() }
            )
        }
    }

    private fun requestKakaoLogin() = lifecycleScope.launch {
        kakaoLogin.login(this@LoginActivity)
            .onSuccess { viewModel.login(it.value) }
            .onFailure { /* no-op */ }
    }

    private fun observeViewModel() {
        viewModel.state
            .filter { it is LoginState.Success }
            .onEach { startSignUpActivity() }
            .launchIn(lifecycleScope)
    }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    private fun startSignUpActivity() {
        SignUpActivity.startActivity(this)
        finish()
    }

    private fun startTermsActivity() = TermsActivity.startActivity(this)
    private fun startPolicyActivity() = PolicyActivity.startActivity(this)

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
}
