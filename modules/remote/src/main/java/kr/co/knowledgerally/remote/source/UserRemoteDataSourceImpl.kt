package kr.co.knowledgerally.remote.source

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.knowledgerally.data.model.BallHistoryEntity
import kr.co.knowledgerally.data.model.NotificationEntity
import kr.co.knowledgerally.data.model.OnboardEntity
import kr.co.knowledgerally.data.model.UserEntity
import kr.co.knowledgerally.data.source.UserRemoteDataSource
import kr.co.knowledgerally.remote.api.ApiService
import kr.co.knowledgerally.remote.image.ImageTranscoder
import kr.co.knowledgerally.remote.model.PushRequest
import kr.co.knowledgerally.remote.model.toData
import kr.co.knowledgerally.remote.model.toRemote
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

internal class UserRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val imageTranscoder: ImageTranscoder,
) : UserRemoteDataSource {

    override suspend fun isOnboarded(): Result<Boolean> = runCatching {
        val response = apiService.isOnboarded()
        response.data.isOnboarded
    }

    override suspend fun submitOnboard(onboard: OnboardEntity): Result<Unit> = runCatching {
        val imageUri = onboard.imageUri
        if (imageUri != null) {
            val image = withContext(Dispatchers.IO) { imageTranscoder.from(imageUri) }
                .map { image ->
                    MultipartBody.Part.createFormData(
                        "image",
                        image.filename,
                        image.data.toRequestBody(MultipartBody.FORM)
                    )
                }
                .getOrThrow()
            apiService.uploadUserImage(image)
        }
        apiService.submitOnboard(onboard.toRemote())
    }

    override suspend fun getUser(): Result<UserEntity> = runCatching {
        val data = apiService.getUser().data
        val imageUrl = data.userImage?.userImageUrl
        data.user.toData(imageUrl)
    }

    override suspend fun updatePushActive(active: Boolean): Result<Unit> = runCatching {
        apiService.updatePushActive(PushRequest(active))
    }

    override suspend fun getBallHistories(): Result<List<BallHistoryEntity>> = runCatching {
        apiService.getBallHistories().data.map { it.toData() }
    }

    override suspend fun getNotifications(): Result<List<NotificationEntity>> = runCatching {
        apiService.getNotifications().notifications.map { it.toData() }
    }
}
