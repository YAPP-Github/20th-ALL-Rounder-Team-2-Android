package kr.co.knowledgerally.ui.schedule

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.ActivityTransition
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@AndroidEntryPoint
class ScheduleActivity : BaseActivity() {

    private val viewModel: ScheduleViewModel by viewModels()

    override val activityTransition: ActivityTransition = ActivityTransition.Cover

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                ScheduleScreen()
            }
        }
    }
}
