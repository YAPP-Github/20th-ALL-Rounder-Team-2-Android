package kr.co.knowledgerally.domain.repo

import kr.co.knowledgerally.domain.model.Lesson

interface LessonRepository {

    suspend fun getPlayerLessons(): Result<List<Lesson>>

    suspend fun getCoachLessons(): Result<List<Lesson>>
}