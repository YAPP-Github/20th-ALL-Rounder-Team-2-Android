package kr.co.knowledgerally.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.applicant.ApplicantScreen
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class UserActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId: Long = intent.getLongExtra(KEY_USER_ID, -1)

        setContent {
            KnowllyTheme {
                ApplicantScreen(
                    url = "",
                    navigateUp = ::navigateUp
                )
            }
        }
    }

    private fun navigateUp() = finish()

    companion object {
        fun getIntent(context: Context, userId: Long): Intent =
            Intent(context, UserActivity::class.java)
                .putExtra(KEY_USER_ID, userId)

        private const val KEY_USER_ID = "KEY_USER_ID"
    }
}
