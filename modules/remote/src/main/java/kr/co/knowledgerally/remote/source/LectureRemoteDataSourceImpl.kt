package kr.co.knowledgerally.remote.source

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.knowledgerally.data.model.LectureInfoEntity
import kr.co.knowledgerally.data.model.LectureStateEntity
import kr.co.knowledgerally.data.model.RegistrationEntity
import kr.co.knowledgerally.data.model.ScheduleEntity
import kr.co.knowledgerally.data.source.LectureRemoteDataSource
import kr.co.knowledgerally.remote.api.ApiService
import kr.co.knowledgerally.remote.image.ImageTranscoder
import kr.co.knowledgerally.remote.model.SchedulesRequest
import kr.co.knowledgerally.remote.model.toData
import kr.co.knowledgerally.remote.model.toRemote
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

internal class LectureRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val imageTranscoder: ImageTranscoder,
) : LectureRemoteDataSource {

    override suspend fun getPlayerLectureInfoList(): Result<List<LectureInfoEntity>> = runCatching {
        apiService
            .getPlayerLectureInfoList()
            .data.map { response ->
                val lectureInfo = response.lectureInfo
                LectureInfoEntity(
                    id = lectureInfo.id,
                    /**
                     * 수강 클래스는, 강의 단위로 클래스를 조회함
                     */
                    lectures = listOf(response.lecture.toData()),
                    topic = lectureInfo.topic,
                    imageUrls = lectureInfo.images.map { it.imageUrl },
                    coach = lectureInfo.coach.user.toData(),
                    category = lectureInfo.category.toData(),
                )
            }
    }

    override suspend fun getCoachLectureInfoList(state: LectureStateEntity?): Result<List<LectureInfoEntity>> =
        runCatching {
            apiService.getCoachLectureInfoList(state?.toRemote())
                .data
                .map { it.toData() }
        }

    override suspend fun register(registration: RegistrationEntity): Result<Long> = runCatching {
        val imageUris = registration.imageUris
        val imageIds = if (imageUris.isNotEmpty()) {
            val parts = withContext(Dispatchers.IO) {
                imageUris.map { imageTranscoder.from(it).getOrThrow() }
            }
                .map { image ->
                    MultipartBody.Part.createFormData(
                        "images",
                        image.filename,
                        image.data.toRequestBody(MultipartBody.FORM)
                    )
                }
            val images = apiService.uploadLectureImages(parts).data
            images.map { it.id }
        } else {
            emptyList()
        }
        val request = registration.toRemote(imageIds)
        apiService.uploadLectureInfo(request).data.id
    }

    override suspend fun register(lectureId: Long, schedules: List<ScheduleEntity>): Result<Unit> =
        runCatching {
            apiService.uploadLectureSchedules(
                lectureId = lectureId,
                request = SchedulesRequest(schedules.map { it.toRemote() })
            )
        }
}
