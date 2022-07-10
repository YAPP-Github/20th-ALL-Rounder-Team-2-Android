package kr.co.knowledgerally.data.source

import kr.co.knowledgerally.data.model.LectureEntity
import kr.co.knowledgerally.data.model.RegistrationEntity

interface LectureRemoteDataSource {

    suspend fun getCoachLectures(): Result<List<LectureEntity>>

    suspend fun getPlayerLectures(): Result<List<LectureEntity>>

    suspend fun register(registration: RegistrationEntity): Result<Long>
}
