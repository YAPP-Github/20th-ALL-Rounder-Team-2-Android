package kr.co.knowledgerally.ui.signup

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
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.component.Loading
import kr.co.knowledgerally.ui.policy.PolicyActivity
import kr.co.knowledgerally.ui.profile.ProfileActivity
import kr.co.knowledgerally.ui.profile.state.Mode
import kr.co.knowledgerally.ui.terms.TermsActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                val uiState by viewModel.uiState.collectAsState()

                Box(modifier = Modifier.fillMaxSize()) {
                    SignUpScreen(
                        navigateUp = ::navigateUp,
                        navigateToTerms = ::startTermsActivity,
                        navigateToPolicy = ::startPolicyActivity,
                        signUp = { viewModel.signUp() }
                    )

                    if (uiState.isLoading) {
                        Loading()
                    }
                }

                uiState.result?.let { result ->
                    LaunchedEffect(result) {
                        if (result.isSuccess) {
                            startProfileActivity()
                        }
                    }
                }
            }
        }
    }

    private fun navigateUp() = finish()

    private fun startTermsActivity() = TermsActivity.startActivity(this)

    private fun startPolicyActivity() = PolicyActivity.startActivity(this)

    private fun startProfileActivity() {
        val intent = ProfileActivity.getIntent(this, Mode.New)
        startActivity(intent)
        finish()
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }

        fun startActivity(context: Context, providerAccessToken: String) {
            val intent = Intent(context, SignUpActivity::class.java)
                .putExtra(SignUpViewModel.KEY_PROVIDER_ACCESS_TOKEN, providerAccessToken)
            context.startActivity(intent)
        }
    }
}
