package kr.co.knowledgerally.data.source

import kr.co.knowledgerally.data.model.LectureEntity

interface LectureRemoteDataSource {

    suspend fun getCoachLectures(): Result<List<LectureEntity>>

    suspend fun getPlayerLectures(): Result<List<LectureEntity>>
}
