package kr.co.knowledgerally.ui.applicant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class ApplicantActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                ApplicantScreen(
                    navigateUp = ::navigateUp
                )
            }
        }
    }

    private fun navigateUp() = finish()

    companion object {
        fun getIntent(context: Context, lectureId: String): Intent =
            Intent(context, ApplicantActivity::class.java)
                .putExtra(KEY_LECTURE_ID, lectureId)

        private const val KEY_LECTURE_ID = "KEY_LECTURE_ID"
    }
}
