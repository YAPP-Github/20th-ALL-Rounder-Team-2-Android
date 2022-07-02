package kr.co.knowledgerally.domain.usecase

import kr.co.knowledgerally.domain.model.Lesson
import kr.co.knowledgerally.domain.repo.LessonRepository
import javax.inject.Inject

class GetCoachLessonListUseCase @Inject constructor(
    private val lessonRepository: LessonRepository
) {

    suspend operator fun invoke(): Result<List<Lesson>> = lessonRepository.getCoachLessons()
}