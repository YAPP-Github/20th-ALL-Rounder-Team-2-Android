package kr.co.knowledgerally.remote.source

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.knowledgerally.data.model.OnboardEntity
import kr.co.knowledgerally.data.source.UserRemoteDataSource
import kr.co.knowledgerally.remote.api.ApiService
import kr.co.knowledgerally.remote.image.ImageTranscoder
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
                .map { byteArray -> byteArray.toRequestBody(MultipartBody.FORM) }
                .map { requestBody ->
                    MultipartBody.Part.createFormData("image", null, requestBody)
                }
                .getOrThrow()
            apiService.uploadImage(image)
        }
        apiService.submitOnboard(onboard.toRemote())
    }
}
