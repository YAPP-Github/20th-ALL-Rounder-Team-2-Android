package kr.co.knowledgerally.ui.register.schedule

import kr.co.knowledgerally.domain.model.Schedule

data class RegisterScheduleUiState(
    val schedules: List<Schedule> = emptyList(),
    val isLoading: Boolean = false,
    val result: RegisterScheduleResult? = null,
) {
    fun addSchedule(schedule: Schedule): RegisterScheduleUiState = copy(
        schedules = schedules + schedule
    )

    fun removeSchedule(schedule: Schedule): RegisterScheduleUiState = copy(
        schedules = schedules.filterNot { it == schedule }
    )

    fun loading(isLoading: Boolean) = copy(isLoading = isLoading)

    operator fun contains(schedule: Schedule): Boolean = schedule in schedules
}
