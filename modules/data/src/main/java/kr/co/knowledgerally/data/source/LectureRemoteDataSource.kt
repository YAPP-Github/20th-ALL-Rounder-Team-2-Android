package kr.co.knowledgerally.data.source

import kr.co.knowledgerally.data.model.LectureEntity
import kr.co.knowledgerally.data.model.LectureStateEntity
import kr.co.knowledgerally.data.model.RegistrationEntity
import kr.co.knowledgerally.data.model.ScheduleEntity

interface LectureRemoteDataSource {

    suspend fun getCoachLectures(state: LectureStateEntity?): Result<List<LectureEntity>>

    suspend fun getPlayerLectures(): Result<List<LectureEntity>>

    suspend fun register(registration: RegistrationEntity): Result<Long>

    suspend fun register(lectureId: Long, schedules: List<ScheduleEntity>): Result<Unit>
}
