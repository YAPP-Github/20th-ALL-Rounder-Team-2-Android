package kr.co.knowledgerally.ui.register.schedule

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.knowledgerally.base.BaseViewModel
import kr.co.knowledgerally.domain.model.Schedule
import kr.co.knowledgerally.domain.usecase.GetLectureScheduleListUseCase
import kr.co.knowledgerally.domain.usecase.RegisterLectureScheduleUseCase
import kr.co.knowledgerally.toast.Toaster
import kr.co.knowledgerally.ui.R
import javax.inject.Inject

@HiltViewModel
class RegisterScheduleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getLectureScheduleListUseCase: GetLectureScheduleListUseCase,
    private val registerLectureScheduleUseCase: RegisterLectureScheduleUseCase,
) : BaseViewModel() {
    private val lectureId: Long = savedStateHandle.get<Long>(KEY_LECTURE_ID)!!

    private val _uiState = MutableStateFlow(RegisterScheduleUiState())
    val uiState: StateFlow<RegisterScheduleUiState> = _uiState.asStateFlow()

    private var job: Job? = null

    init {
        launch {
            _uiState.update { it.loading(true) }
            val schedules = getLectureScheduleListUseCase(lectureId).getOrDefault(emptyList())
            _uiState.update {
                it.copy(
                    isLoading = false,
                    schedules = schedules
                )
            }
        }
    }

    fun addSchedule(schedule: Schedule) {
        if (schedule in uiState.value) {
            Toaster.show(R.string.register_schedule_already_contains_schedule)
            return
        }
        _uiState.update { it.addSchedule(schedule) }
    }

    fun removeSchedule(schedule: Schedule) {
        _uiState.update { it.removeSchedule(schedule) }
    }

    fun register() {
        if (job != null) return

        job = launch {
            _uiState.update { it.loading(true) }
            val result = registerLectureScheduleUseCase(lectureId, uiState.value.schedules)
                .onSuccess { Toaster.show(R.string.register_schedule_complete) }
                .onFailure { handleException(it) }
                .map { RegisterScheduleResult.Success }
                .getOrNull()

            _uiState.update { uiState ->
                uiState.copy(
                    isLoading = false,
                    result = result,
                )
            }
        }
        job?.invokeOnCompletion { job = null }
    }

    companion object {
        const val KEY_LECTURE_ID = "lectureId"
    }
}
