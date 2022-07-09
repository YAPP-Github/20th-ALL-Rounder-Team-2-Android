package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.data.model.toDomain
import kr.co.knowledgerally.data.source.LectureRemoteDataSource
import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

internal class LectureRepositoryImpl @Inject constructor(
    private val lectureRemoteDataSource: LectureRemoteDataSource
) : LectureRepository {

    override suspend fun getCoachLectures(): Result<List<Lecture>> =
        lectureRemoteDataSource.getCoachLectures()
            .mapCatching { lectures -> lectures.map { it.toDomain() } }

    override suspend fun getPlayerLectures(): Result<List<Lecture>> =
        lectureRemoteDataSource.getPlayerLectures()
            .mapCatching { lectures -> lectures.map { it.toDomain() } }
}
