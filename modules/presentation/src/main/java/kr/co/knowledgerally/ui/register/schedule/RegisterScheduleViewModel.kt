package kr.co.knowledgerally.ui.register.schedule

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.Schedule
import kr.co.knowledgerally.toast.Toaster
import javax.inject.Inject

@HiltViewModel
class RegisterScheduleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    private val lectureId: Long = savedStateHandle.get<Long>(KEY_LECTURE_ID)!!

    private val _schedules = MutableStateFlow<List<Schedule>>(emptyList())
    val schedules = _schedules.asStateFlow()

    fun addSchedule(schedule: Schedule) {
        if (schedule in schedules.value) {
            Toaster.show("이미 추가된 일정입니다.")
            return
        }
        _schedules.update { it + schedule }
    }

    fun removeSchedule(schedule: Schedule) {
        _schedules.update { schedules ->
            schedules.filterNot { it == schedule }
        }
    }

    fun register() {
        if (schedules.value.isEmpty()) {
            return
        }
        Toaster.show("클래스 등록이 완료되었습니다.")
    }

    companion object {
        const val KEY_LECTURE_ID = "lectureId"
    }
}
