package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.ApplicantEntity

data class FormResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("content")
    val content: String,
    @SerializedName("lecture")
    val lecture: LectureResponse,
    @SerializedName("user")
    val user: UserResponse,
    @SerializedName("userImage")
    val userImage: UserImageResponse,
    @SerializedName("state")
    val state: String, // REQUEST, ACCEPT, REJECT
    @SerializedName("expirationDate")
    val expirationDate: String
) {

    enum class State {
        @SerializedName("REQUEST") Request,
        @SerializedName("ACCEPT") Accept,
        @SerializedName("REJECT") Reject
    }
}

internal fun FormResponse.toData() = ApplicantEntity(
    id = id,
    name = user.username,
    content = content,
    imageUrl = userImage.userImageUrl,
    startAt = lecture.startAt
)
