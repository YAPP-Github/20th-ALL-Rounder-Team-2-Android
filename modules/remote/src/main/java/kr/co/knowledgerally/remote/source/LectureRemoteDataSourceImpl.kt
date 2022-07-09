package kr.co.knowledgerally.remote.source

import kr.co.knowledgerally.data.model.LectureEntity
import kr.co.knowledgerally.data.source.LectureRemoteDataSource
import kr.co.knowledgerally.remote.api.ApiService
import kr.co.knowledgerally.remote.model.toData
import javax.inject.Inject

internal class LectureRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : LectureRemoteDataSource {

    override suspend fun getPlayerLectures(): Result<List<LectureEntity>> = runCatching {
        apiService.getPlayerLectures().lectures.map { it.toData() }
    }

    override suspend fun getCoachLectures(): Result<List<LectureEntity>> = runCatching {
        apiService.getCoachLectures().lectures.map { it.toData() }
    }
}
