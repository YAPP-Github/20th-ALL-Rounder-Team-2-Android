package kr.co.knowledgerally.ui.schedule

import android.content.Intent
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kr.co.knowledgerally.domain.model.Schedule
import java.time.LocalDateTime

@Parcelize
data class ScheduleResult(
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
) : Parcelable {

    companion object {
        private const val KEY = "KEY_SCHEDULE_RESULT"

        fun toIntent(schedule: Schedule): Intent = Intent().putExtra(
            KEY,
            ScheduleResult(startAt = schedule.startAt, endAt = schedule.endAt)
        )

        fun from(intent: Intent?): ScheduleResult? = intent?.getParcelableExtra(KEY)
    }
}
