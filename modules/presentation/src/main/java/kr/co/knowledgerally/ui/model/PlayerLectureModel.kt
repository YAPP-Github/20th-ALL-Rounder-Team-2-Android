package kr.co.knowledgerally.ui.model

import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.model.User

sealed interface PlayerLectureModel {

    val lecture: LectureInfoModel
    val coach: User

    data class Matching(
        override val lecture: LectureInfoModel,
        override val coach: User
    ) : PlayerLectureModel

    data class Scheduled(
        override val lecture: LectureInfoModel,
        override val coach: User
    ) : PlayerLectureModel

    data class Completed(
        override val lecture: LectureInfoModel,
        override val coach: User,
        val isReviewed: Boolean
    ) : PlayerLectureModel
}

fun Lecture.toPlayerPresentation(): PlayerLectureModel =
    when (this) {
        is Lecture.Onboard -> PlayerLectureModel.Matching(
            lecture = lecture.toPresentation(),
            coach = coach
        )
        is Lecture.Ongoing -> PlayerLectureModel.Scheduled(
            lecture = lecture.toPresentation(),
            coach = coach
        )
        is Lecture.Done -> PlayerLectureModel.Completed(
            lecture = lecture.toPresentation(),
            coach = coach,
            isReviewed = isReviewed
        )
    }
