package kr.co.knowledgerally.ui.lecture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.model.LectureNavigationType
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class LectureActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lectureInfoId: Long = intent.getLongExtra(
            KEY_LECTURE_INFO_ID,
            DEFAULT_LONG_EXTRA_VALUE
        )
        if (lectureInfoId == DEFAULT_LONG_EXTRA_VALUE) {
            handleException(IllegalStateException("${TAG}에서 lectureInfoId를 찾을 수 없습니다."))
            return
        }

        if (intent.hasExtra(KEY_NAVIGATION_TYPE).not()) {
            handleException(IllegalStateException("${TAG}에서 navigationType을 찾을 수 없습니다."))
            return
        }
        val navigationType: LectureNavigationType =
            intent.getSerializableExtra(KEY_NAVIGATION_TYPE) as LectureNavigationType


        setContent {
            KnowllyTheme {
                LectureScreen(
                    url = "",
                    navigateUp = ::navigateUp
                )
            }
        }
    }

    private fun navigateUp() = finish()

    companion object {
        fun getIntent(
            context: Context,
            lectureInfoId: Long,
            navigationType: LectureNavigationType
        ): Intent =
            Intent(context, LectureActivity::class.java)
                .putExtra(KEY_LECTURE_INFO_ID, lectureInfoId)
                .putExtra(KEY_NAVIGATION_TYPE, navigationType)

        private const val KEY_LECTURE_INFO_ID = "KEY_LECTURE_INFO_ID"
        private const val KEY_NAVIGATION_TYPE = "KEY_NAVIGATION_TYPE"
        private const val DEFAULT_LONG_EXTRA_VALUE: Long = -1
    }
}