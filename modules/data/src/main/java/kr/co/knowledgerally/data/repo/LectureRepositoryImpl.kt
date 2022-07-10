package kr.co.knowledgerally.data.repo

import kr.co.knowledgerally.data.model.toData
import kr.co.knowledgerally.data.model.toDomain
import kr.co.knowledgerally.data.source.LectureRemoteDataSource
import kr.co.knowledgerally.domain.model.LectureLegacy
import kr.co.knowledgerally.domain.model.LectureState
import kr.co.knowledgerally.domain.model.Registration
import kr.co.knowledgerally.domain.model.Schedule
import kr.co.knowledgerally.domain.repo.LectureRepository
import javax.inject.Inject

internal class LectureRepositoryImpl @Inject constructor(
    private val lectureRemoteDataSource: LectureRemoteDataSource
) : LectureRepository {

    override suspend fun getCoachLectures(state: LectureState?): Result<List<LectureLegacy>> =
        lectureRemoteDataSource
            .getCoachLectures(state?.toData())
            .mapCatching { lectures -> lectures.map { it.toDomain() } }

    override suspend fun getPlayerLectures(): Result<List<LectureLegacy>> =
        lectureRemoteDataSource.getPlayerLectures()
            .mapCatching { lectures -> lectures.map { it.toDomain() } }

    override suspend fun register(registration: Registration): Result<Long> =
        lectureRemoteDataSource.register(registration.toData())

    override suspend fun register(lectureId: Long, schedules: List<Schedule>): Result<Unit> =
        lectureRemoteDataSource.register(lectureId, schedules.map { it.toData() })
}
