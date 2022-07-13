package kr.co.knowledgerally.ui.review

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class ReviewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lectureId: Long = intent.getLongExtra(
            KEY_LECTURE_ID,
            DEFAULT_LONG_EXTRA_VALUE
        )
        if (lectureId == DEFAULT_LONG_EXTRA_VALUE) {
            handleException(IllegalStateException("${TAG}에서 lectureId를 찾을 수 없습니다."))
            return
        }

        setContent {
            KnowllyTheme {
                ReviewScreen(
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
        private const val DEFAULT_LONG_EXTRA_VALUE: Long = -1
    }
}