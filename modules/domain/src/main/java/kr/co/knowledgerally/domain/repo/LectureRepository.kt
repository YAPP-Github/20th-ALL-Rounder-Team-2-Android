package kr.co.knowledgerally.domain.repo

import kr.co.knowledgerally.domain.model.Lecture

interface LectureRepository {

    suspend fun getPlayerLectures(): Result<List<Lecture>>

    suspend fun getCoachLectures(): Result<List<Lecture>>
}
