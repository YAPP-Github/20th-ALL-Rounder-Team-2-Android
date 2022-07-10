package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.LectureEntityLegacy
import kr.co.knowledgerally.data.model.LectureInfoEntityLegacy

data class PlayerLectureResponse(
    @SerializedName("form")
    val form: FormResponse,
    @SerializedName("lectureInformation")
    val lectureInfo: LectureInfoResponseLegacy
)

internal fun PlayerLectureResponse.toLectureInfoEntity() =
    LectureInfoEntityLegacy(
        id = lectureInfo.id,
        title = lectureInfo.title,
        imageUrls = lectureInfo.images.map { it.imageUrl },
        startAt = form.lecture.startAt,
        endAt = form.lecture.endAt
    )

internal fun PlayerLectureResponse.toData(): LectureEntityLegacy =
    when (form.lecture.state) {
        LectureResponseLegacy.State.Onboard -> {
            LectureEntityLegacy.Onboard(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(),
                applicants = emptyList()
            )
        }
        LectureResponseLegacy.State.Ongoing -> {
            LectureEntityLegacy.Ongoing(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(),
                player = form.user.toData()
            )
        }
        LectureResponseLegacy.State.Done -> {
            LectureEntityLegacy.Done(
                lecture = toLectureInfoEntity(),
                coach = lectureInfo.coach.user.toData(),
                player = form.user.toData(),
                isReviewed = form.lecture.isReviewed
            )
        }
    }
