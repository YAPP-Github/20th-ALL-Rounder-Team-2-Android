package kr.co.knowledgerally.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
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

        lifecycleScope.launch {
            // 화면에 진입할 때 마다 로그아웃 처리
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                kakaoLogin.logout()
            }
        }
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

    private fun observeViewModel() = lifecycleScope.launch {
        viewModel.state.collect { state ->
            when (state) {
                is LoginState.NeedToSignUp -> startSignUpActivity(state.providerAccessToken)
                LoginState.NotLoggedIn -> Unit // TODO
                LoginState.Success -> Unit // TODO
            }
        }
    }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    private fun startSignUpActivity(providerAccessToken: String) {
        SignUpActivity.startActivity(this, providerAccessToken)
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
