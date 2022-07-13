package kr.co.knowledgerally.ui.lecture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseActivity
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

        if (intent.hasExtra(KEY_LECTURE_TYPE).not()) {
            handleException(IllegalStateException("${TAG}에서 lectureType을 찾을 수 없습니다."))
            return
        }
        val lectureType: LectureType =
            intent.getSerializableExtra(KEY_LECTURE_TYPE) as LectureType


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
            lectureType: LectureType
        ): Intent =
            Intent(context, LectureActivity::class.java)
                .putExtra(KEY_LECTURE_INFO_ID, lectureInfoId)
                .putExtra(KEY_LECTURE_TYPE, lectureType)

        private const val KEY_LECTURE_INFO_ID = "KEY_LECTURE_INFO_ID"
        private const val KEY_LECTURE_TYPE = "KEY_LECTURE_TYPE"
        private const val DEFAULT_LONG_EXTRA_VALUE: Long = -1
    }
}