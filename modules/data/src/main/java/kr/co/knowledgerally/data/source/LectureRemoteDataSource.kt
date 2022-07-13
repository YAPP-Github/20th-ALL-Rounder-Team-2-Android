package kr.co.knowledgerally.data.source

import kr.co.knowledgerally.data.model.LectureInfoEntity
import kr.co.knowledgerally.data.model.LectureStateEntity
import kr.co.knowledgerally.data.model.RegistrationEntity
import kr.co.knowledgerally.data.model.ScheduleEntity

interface LectureRemoteDataSource {

    suspend fun getCoachLectureInfoList(state: LectureStateEntity?): Result<List<LectureInfoEntity>>

    suspend fun getPlayerLectureInfoList(): Result<List<LectureInfoEntity>>

    suspend fun register(registration: RegistrationEntity): Result<Long>

    suspend fun register(lectureId: Long, schedules: List<ScheduleEntity>): Result<Unit>
}
