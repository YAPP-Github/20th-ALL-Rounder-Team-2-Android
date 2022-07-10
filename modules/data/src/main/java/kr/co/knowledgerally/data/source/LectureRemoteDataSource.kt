package kr.co.knowledgerally.data.source

import kr.co.knowledgerally.data.model.LectureEntityLegacy
import kr.co.knowledgerally.data.model.LectureStateEntity
import kr.co.knowledgerally.data.model.RegistrationEntity
import kr.co.knowledgerally.data.model.ScheduleEntity

interface LectureRemoteDataSource {

    suspend fun getCoachLectures(state: LectureStateEntity?): Result<List<LectureEntityLegacy>>

    suspend fun getPlayerLectures(): Result<List<LectureEntityLegacy>>

    suspend fun register(registration: RegistrationEntity): Result<Long>

    suspend fun register(lectureId: Long, schedules: List<ScheduleEntity>): Result<Unit>
}
