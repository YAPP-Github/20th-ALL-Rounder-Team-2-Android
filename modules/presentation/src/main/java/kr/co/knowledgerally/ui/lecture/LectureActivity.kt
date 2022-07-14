package kr.co.knowledgerally.ui.lecture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.BaseWebViewActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class LectureActivity : BaseWebViewActivity() {

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

        val initUrl = "$BASE_URL/lecture-detail/$lectureInfoId"
        
        setContent {
            KnowllyTheme {
                LectureScreen(
                    webAppInterface = webAppInterface,
                    initUrl = initUrl,
                    navigateUp = ::navigateUp
                )
            }
        }
    }

    companion object {
        fun getIntent(
            context: Context,
            lectureInfoId: Long
        ): Intent =
            Intent(context, LectureActivity::class.java)
                .putExtra(KEY_LECTURE_INFO_ID, lectureInfoId)

        private const val KEY_LECTURE_INFO_ID = "KEY_LECTURE_INFO_ID"
        private const val DEFAULT_LONG_EXTRA_VALUE: Long = -1
    }
}
