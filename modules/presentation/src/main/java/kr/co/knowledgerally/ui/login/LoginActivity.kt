package kr.co.knowledgerally.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.domain.model.LoginResult
import kr.co.knowledgerally.domain.model.ProviderToken
import kr.co.knowledgerally.feature.kakao.KakaoLogin
import kr.co.knowledgerally.ui.component.Loading
import kr.co.knowledgerally.ui.main.MainActivity
import kr.co.knowledgerally.ui.policy.PolicyActivity
import kr.co.knowledgerally.ui.profile.ProfileActivity
import kr.co.knowledgerally.ui.profile.state.Mode
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

        val uiState by viewModel.uiState.collectAsState()

        KnowllyTheme {
            Box(modifier = Modifier.fillMaxSize()) {
                LoginScreen(
                    onLogin = { requestKakaoLogin() },
                    navigateToTerms = { startTermsActivity() },
                    navigateToPolicy = { startPolicyActivity() }
                )
                if (uiState.isLoading) {
                    Loading()
                }
            }

            uiState.result?.let { result ->
                LaunchedEffect(result) {
                    when (result) {
                        LoginResult.Signed -> startMainActivity()
                        LoginResult.Onboard -> startProfileActivity()
                        is LoginResult.NotSigned -> startSignUpActivity(result.providerToken)
                    }
                }
            }
        }
    }

    private fun requestKakaoLogin() = lifecycleScope.launch {
        kakaoLogin.login(this@LoginActivity)
            .onSuccess { viewModel.login(it.value) }
            .onFailure { /* no-op */ }
    }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    private fun startProfileActivity() {
        val intent = ProfileActivity.getIntent(this, mode = Mode.New)
        startActivity(intent)
        finish()
    }

    private fun startSignUpActivity(providerToken: ProviderToken) {
        SignUpActivity.startActivity(this, providerToken.accessToken)
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
