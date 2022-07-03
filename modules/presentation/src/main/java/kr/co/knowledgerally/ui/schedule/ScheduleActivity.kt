package kr.co.knowledgerally.ui.schedule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kr.co.knowledgerally.base.ActivityTransition
import kr.co.knowledgerally.base.BaseActivity
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ScheduleActivity : BaseActivity() {

    private val viewModel: ScheduleViewModel by viewModels()

    override val activityTransition: ActivityTransition = ActivityTransition.Cover

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KnowllyTheme {
                ScheduleScreen(
                    viewModel = viewModel,
                    navigateUp = ::finish,
                    showDatePicker = ::showDatePicker
                )
            }
        }
    }

    private fun showDatePicker() {
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val dialog = MaterialDatePicker.Builder
            .datePicker()
            .setSelection(today)
            .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
            .setCalendarConstraints(
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointForward.now())
                    .setStart(today)
                    .setEnd(today + TimeUnit.DAYS.toMillis(30))
                    .build()
            )
            .setTheme(R.style.ThemeOverlay_App_DatePicker)
            .build()

        dialog.addOnPositiveButtonClickListener { timeInMillis ->
            val localDate =
                Instant.ofEpochMilli(timeInMillis).atZone(ZoneId.systemDefault()).toLocalDate()
            viewModel.select(localDate)
        }
        dialog.show(supportFragmentManager, TAG_DIALOG)
    }

    companion object {
        private const val TAG_DIALOG = "TAG_DIALOG"

        fun getIntent(context: Context): Intent = Intent(context, ScheduleActivity::class.java)
    }
}
