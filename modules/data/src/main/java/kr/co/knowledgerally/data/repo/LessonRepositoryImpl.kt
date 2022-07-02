package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.domain.model.Lesson
import kr.co.knowledgerally.domain.repo.LessonRepository
import javax.inject.Inject

internal class LessonRepositoryImpl @Inject constructor(): LessonRepository {

    override suspend fun getCoachLessons(): Result<List<Lesson>> = runCatching { emptyList() }

    override suspend fun getPlayerLessons(): Result<List<Lesson>> = runCatching { emptyList() }
}
