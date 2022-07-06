package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.model.LectureBundle
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

class GetPlayerLectureBundleUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
) {

    suspend operator fun invoke(): Result<LectureBundle> =
        lectureRepository.getPlayerLectures().map {
            LectureBundle(
                onboardingLectures = it.filterIsInstance<Lecture.Onboard>(),
                ongoingLectures = it.filterIsInstance<Lecture.Ongoing>(),
                doneLectures = it.filterIsInstance<Lecture.Done>()
            )
        }
}
