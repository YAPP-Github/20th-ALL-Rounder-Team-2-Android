package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.RegistrationEntity

data class RegistrationRequest(
    @SerializedName("category")
    val category: CategoryResponse,
    @SerializedName("topic")
    val topic: String,
    @SerializedName("introduce")
    val introduce: String,
    @SerializedName("tags")
    val tags: List<TagRequest>,
    @SerializedName("lectureImages")
    val images: List<ImageRequest>,
)

fun RegistrationEntity.toRemote(imageIds: List<Long>) = RegistrationRequest(
    category = category.toRemote(),
    topic = topic,
    introduce = introduce,
    tags = tags.map { TagRequest(content = it) },
    images = imageIds.map { ImageRequest(id = it) }
)
