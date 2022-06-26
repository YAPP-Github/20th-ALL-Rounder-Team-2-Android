package kr.co.knowledgerally.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.policy.PolicyActivity
import kr.co.knowledgerally.ui.profile.ProfileActivity
import kr.co.knowledgerally.ui.terms.TermsActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                SignUpScreen(
                    navigateUp = ::navigateUp,
                    navigateToTerms = ::startTermsActivity,
                    navigateToPolicy = ::startPolicyActivity,
                    signUp = { pushActive -> viewModel.signUp(pushActive) }
                )
            }
        }

        lifecycleScope.launch {
            viewModel.result.collect { result ->
                when (result) {
                    SignUpResult.Ready -> Unit // no-op
                    SignUpResult.Success -> startProfileActivity()
                }
            }
        }
    }

    private fun navigateUp() = finish()

    private fun startTermsActivity() = TermsActivity.startActivity(this)

    private fun startPolicyActivity() = PolicyActivity.startActivity(this)

    private fun startProfileActivity() {
        ProfileActivity.startActivity(this)
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
