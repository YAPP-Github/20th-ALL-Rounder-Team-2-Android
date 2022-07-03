package kr.co.knowledgerally.ui.schedule

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.Schedule
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor() : BaseViewModel() {
    private val _selectedDate = MutableStateFlow<LocalDate?>(null)
    val selectedDate = _selectedDate.asStateFlow()

    private val _schedule = MutableStateFlow<Schedule?>(null)
    val schedule = _schedule.asStateFlow()

    fun select(date: LocalDate) {
        _selectedDate.value = date
    }

    fun updateSchedule(schedule: Schedule) {
        _schedule.value = schedule
    }
}
