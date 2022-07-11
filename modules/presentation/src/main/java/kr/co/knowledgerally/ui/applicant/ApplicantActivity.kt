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

        val lectureInfoId = runCatching { intent.getStringExtra(KEY_LECTURE_INFO_ID)!! }
            .getOrElse { handleException(it) }

        setContent {
            KnowllyTheme {
                ApplicantScreen(
                    url = "",
                    navigateUp = ::navigateUp,
                )
            }
        }
    }

    private fun navigateUp() = finish()

    companion object {
        fun getIntent(context: Context, lectureInfoId: Long): Intent =
            Intent(context, ApplicantActivity::class.java)
                .putExtra(KEY_LECTURE_INFO_ID, lectureInfoId.toString())

        private const val KEY_LECTURE_INFO_ID = "KEY_LECTURE_INFO_ID"
    }
}
