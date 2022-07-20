package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.model.LectureState
import kr.co.knowledgerally.domain.model.Schedule
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

class GetLectureScheduleListUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {

    suspend operator fun invoke(lectureInfoId: Long): Result<List<Schedule>> = lectureRepository
        .getCoachLectureInfoList(LectureState.Onboard)
        .mapCatching { lectureInfoList ->
            lectureInfoList.find { it.id == lectureInfoId }
                ?.lectures
                ?.filterIsInstance<Lecture.Onboard>()
                ?.map { it.schedule }
                ?: emptyList()
        }
}
