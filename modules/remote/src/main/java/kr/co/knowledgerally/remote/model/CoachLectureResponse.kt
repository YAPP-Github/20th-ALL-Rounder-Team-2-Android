package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.LectureEntityLegacy
import kr.co.knowledgerally.data.model.LectureInfoEntityLegacy

data class CoachLectureResponse(
    @SerializedName("lecture")
    val lecture: LectureResponseLegacy,
    @SerializedName("lectureInformation")
    val lectureInfo: LectureInfoResponseLegacy,
    @SerializedName("forms")
    val forms: List<FormResponse>,
    @SerializedName("matechedUser")
    val matchedUser: UserResponse
)

internal fun CoachLectureResponse.toLectureInfoEntity() = LectureInfoEntityLegacy(
    id = lectureInfo.id,
    title = lectureInfo.title,
    imageUrls = lectureInfo.images.map { it.imageUrl },
    startAt = lecture.startAt,
    endAt = lecture.endAt
)

internal fun CoachLectureResponse.toData(): LectureEntityLegacy =
    when (lecture.state) {
        LectureResponseLegacy.State.Onboard -> {
            LectureEntityLegacy.Onboard(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(),
                applicants = forms.map { it.toData() }
            )
        }
        LectureResponseLegacy.State.Ongoing -> {
            LectureEntityLegacy.Ongoing(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(),
                player = matchedUser.toData()
            )
        }
        LectureResponseLegacy.State.Done -> {
            LectureEntityLegacy.Done(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(),
                player = matchedUser.toData(),
                isReviewed = lecture.isReviewed
            )
        }
    }
