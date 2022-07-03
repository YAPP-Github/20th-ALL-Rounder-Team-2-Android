package kr.co.knowledgerally.ui.schedule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kr.co.knowledgerally.domain.model.TimePeriod
import java.time.LocalDate

@Stable
class ScheduleState(
    val date: LocalDate?
) {
    val hasDate: Boolean get() = date != null
    var hours: String by mutableStateOf("")
    var minutes by mutableStateOf("")
    var timePeriod by mutableStateOf(TimePeriod.PM)
    var runningTime: Int? by mutableStateOf(null)
    val runningTimes = List(5) { it + 1 }

    val canAdd: Boolean by derivedStateOf {
        hasDate && hours.isNotBlank() && minutes.isNotBlank() && runningTime != null
    }
}

@Composable
fun rememberScheduleState(
    selectedDate: LocalDate?
) = remember(selectedDate) { ScheduleState(selectedDate) }
