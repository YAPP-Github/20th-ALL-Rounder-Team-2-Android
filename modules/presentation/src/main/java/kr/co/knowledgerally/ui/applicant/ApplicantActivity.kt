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

        val lectureInfoId: Long = intent.getLongExtra(KEY_LECTURE_INFO_ID, DEFAULT_LONG_EXTRA_VALUE)
        if (lectureInfoId == DEFAULT_LONG_EXTRA_VALUE) {
            handleException(Throwable(message = "${TAG}에서 lectureInfoId를 찾을 수 없습니다."))
            return@onCreate
        }

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
        fun getIntent(context: Context, lectureInfoId: Long): Intent =
            Intent(context, ApplicantActivity::class.java)
                .putExtra(KEY_LECTURE_INFO_ID, lectureInfoId)

        private const val KEY_LECTURE_INFO_ID = "KEY_LECTURE_INFO_ID"
        private const val DEFAULT_LONG_EXTRA_VALUE: Long = -1
    }
}
