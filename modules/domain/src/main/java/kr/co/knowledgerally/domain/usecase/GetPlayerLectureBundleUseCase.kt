package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.LectureLegacy
import kr.co.knowledgerally.domain.model.LectureBundle
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

class GetPlayerLectureBundleUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
) {

    suspend operator fun invoke(): Result<LectureBundle> =
        lectureRepository.getPlayerLectures().map {
            LectureBundle(
                onboardingLectures = it.filterIsInstance<LectureLegacy.Onboard>(),
                ongoingLectures = it.filterIsInstance<LectureLegacy.Ongoing>(),
                doneLectures = it.filterIsInstance<LectureLegacy.Done>()
            )
        }
}
