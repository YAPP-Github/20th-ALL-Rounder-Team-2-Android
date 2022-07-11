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

        val lectureInfoId: Long = intent.getLongExtra(KEY_LECTURE_INFO_ID, -1)
        val type: String = intent.getStringExtra(KEY_TYPE)!!

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
        fun getIntent(context: Context, lectureInfoId: Long, type: String): Intent =
            Intent(context, LectureActivity::class.java)
                .putExtra(KEY_LECTURE_INFO_ID, lectureInfoId)
                .putExtra(KEY_TYPE, type)

        private const val KEY_LECTURE_INFO_ID = "KEY_LECTURE_INFO_ID"
        private const val KEY_TYPE = "KEY_TYPE"
    }
}