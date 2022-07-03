package kr.co.knowledgerally.ui.schedule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kr.co.knowledgerally.domain.model.Schedule
import kr.co.knowledgerally.domain.model.TimePeriod
import java.time.LocalDate
import java.time.LocalTime

@Stable
class ScheduleState(
    val date: LocalDate?
) {
    val hasDate: Boolean get() = date != null
    var hour: String by mutableStateOf("")
    var minute by mutableStateOf("")
    var timePeriod by mutableStateOf(TimePeriod.PM)
    var runningTime: Int? by mutableStateOf(null)
    val runningTimes = List(5) { it + 1 }

    val canAdd: Boolean by derivedStateOf {
        hasDate && hour.isNotBlank() && minute.isNotBlank() && runningTime != null
    }

    fun schedule(): Schedule? {
        if (date == null || runningTime == null) {
            return null
        }
        val startAt = date.atTime(LocalTime.of(hour.toInt(), minute.toInt()))
        val endAt = startAt.plusHours(runningTime!!.toLong())
        return Schedule(startAt, endAt)
    }
}

@Composable
fun rememberScheduleState(
    selectedDate: LocalDate?
) = remember(selectedDate) { ScheduleState(selectedDate) }
