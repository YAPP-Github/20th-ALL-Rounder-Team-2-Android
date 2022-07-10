package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Schedule
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

class RegisterLectureScheduleUseCase @Inject constructor(
    private val repository: LectureRepository,
) {

    suspend operator fun invoke(lectureId: Long, schedules: List<Schedule>) =
        repository.register(lectureId, schedules)
}
