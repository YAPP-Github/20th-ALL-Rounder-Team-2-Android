package kr.co.knowledgerally.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.login.SignUpScreen
import kr.co.knowledgerally.ui.policy.PolicyActivity
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
                    viewModel = viewModel,
                    onShowTerms = { startTermsActivity() },
                    onShowPolicy = { startPolicyActivity() }
                )
            }
        }
    }

    private fun startTermsActivity() = TermsActivity.startActivity(this)
    private fun startPolicyActivity() = PolicyActivity.startActivity(this)

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }
    }
}
