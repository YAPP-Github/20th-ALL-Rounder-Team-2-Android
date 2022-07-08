package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.LectureEntity
import kr.co.knowledgerally.data.model.LectureInfoEntity

data class CoachLectureResponse(
    @SerializedName("lecture")
    val lecture: LectureResponse,
    @SerializedName("lectureInformation")
    val lectureInfo: LectureInfoResponse,
    @SerializedName("forms")
    val forms: List<FormResponse>,
    @SerializedName("matechedUser")
    val matchedUser: UserResponse
)

internal fun CoachLectureResponse.toLectureInfoEntity() = LectureInfoEntity(
    id = lectureInfo.id,
    title = lectureInfo.title,
    imageUrls = lectureInfo.images.map { it.url },
    startAt = lecture.startAt,
    endAt = lecture.endAt
)

internal fun CoachLectureResponse.toData(): LectureEntity =
    when (lecture.state) {
        "ON_BOARD" -> {
            LectureEntity.Onboard(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(imageUrl = null),
                applicants = forms.map { it.toData() }
            )
        }
        "ON_GOING" -> {
            LectureEntity.Ongoing(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(imageUrl = null),
                player = matchedUser.toData(forms[0].userImage.userImageUrl)
            )
        }
        else -> {
            LectureEntity.Done(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(imageUrl = null),
                player = matchedUser.toData(forms[0].userImage.userImageUrl),
                isReviewed = lecture.isReviewed
            )
        }
    }