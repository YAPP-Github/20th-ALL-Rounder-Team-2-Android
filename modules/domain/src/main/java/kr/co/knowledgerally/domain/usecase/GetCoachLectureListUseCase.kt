package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

class GetCoachLectureListUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
) {

    suspend operator fun invoke(): Result<List<Lecture>> = lectureRepository.getCoachLectures()
}