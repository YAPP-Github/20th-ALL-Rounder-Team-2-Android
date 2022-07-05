package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.model.LectureBundle
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

class GetCoachLectureListUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
) {

    suspend operator fun invoke(): Result<LectureBundle> = runCatching {
        val lectures = lectureRepository.getCoachLectures().getOrThrow()

        LectureBundle(
            onboardingLectures = lectures.filterIsInstance<Lecture.Onboard>(),
            ongoingLectures = lectures.filterIsInstance<Lecture.Ongoing>(),
            doneLectures = lectures.filterIsInstance<Lecture.Done>()
        )
    }
}
