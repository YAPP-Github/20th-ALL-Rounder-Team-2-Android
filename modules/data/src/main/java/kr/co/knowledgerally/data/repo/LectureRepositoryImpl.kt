package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

internal class LectureRepositoryImpl @Inject constructor() : LectureRepository {

    override suspend fun getCoachLectures(): Result<List<Lecture>> = runCatching { emptyList() }

    override suspend fun getPlayerLectures(): Result<List<Lecture>> = runCatching { emptyList() }
}
