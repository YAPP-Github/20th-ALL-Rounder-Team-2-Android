package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.LectureLegacy
import kr.co.knowledgerally.domain.model.LectureState
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

class GetCoachOnboardLectureListUseCase @Inject constructor(
    private val lectureRepository: LectureRepository,
) {
    suspend operator fun invoke(): Result<List<LectureLegacy.Onboard>> = lectureRepository
        .getCoachLectures(LectureState.Onboard)
        .map { lectures -> lectures.filterIsInstance<LectureLegacy.Onboard>() }
}
