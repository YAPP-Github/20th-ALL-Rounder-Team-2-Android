package kr.co.knowledgerally.ui.review

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.applicant.ApplicantScreen
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class ReviewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lectureId: Long = intent.getLongExtra(KEY_LECTURE_ID, -1)

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
        fun getIntent(context: Context, lectureId: Long): Intent =
            Intent(context, ReviewActivity::class.java)
                .putExtra(KEY_LECTURE_ID, lectureId)

        private const val KEY_LECTURE_ID = "KEY_LECTURE_ID"
    }
}