package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.LectureEntity
import kr.co.knowledgerally.data.model.LectureInfoEntity

data class PlayerLectureResponse(
    @SerializedName("form")
    val form: FormResponse,
    @SerializedName("lectureInformation")
    val lectureInfo: LectureInfoResponse
)

internal fun PlayerLectureResponse.toLectureInfoEntity() =
    LectureInfoEntity(
        id = lectureInfo.id,
        title = lectureInfo.title,
        imageUrls = lectureInfo.images.map { it.imageUrl },
        startAt = form.lecture.startAt,
        endAt = form.lecture.endAt
    )

internal fun PlayerLectureResponse.toData(): LectureEntity =
    when (form.lecture.state) {
        LectureResponse.State.Onboard -> {
            LectureEntity.Onboard(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(imageUrl = null),
                applicants = emptyList()
            )
        }
        LectureResponse.State.Ongoing -> {
            LectureEntity.Ongoing(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(imageUrl = null),
                player = form.user.toData(imageUrl = null)
            )
        }
        LectureResponse.State.Done -> {
            LectureEntity.Done(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(imageUrl = null),
                player = form.user.toData(imageUrl = null),
                isReviewed = form.lecture.isReviewed
            )
        }
    }