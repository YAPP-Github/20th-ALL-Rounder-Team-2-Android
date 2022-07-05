package kr.co.knowledgerally.ui.model

import kr.co.knowledgerally.domain.model.Applicant
import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.model.User

sealed interface CoachLectureModel {

    data class Matching(
        val lecture: LectureInfoModel,
        val applicants: List<Applicant>
    ) : CoachLectureModel

    data class Scheduled(
        val lecture: LectureInfoModel,
        val player: User
    ) : CoachLectureModel

    data class Completed(
        val lecture: LectureInfoModel,
        val player: User,
        val isReviewed: Boolean
    ) : CoachLectureModel
}

fun Lecture.toCoachPresentation(): CoachLectureModel =
    when (this) {
        is Lecture.Onboard -> CoachLectureModel.Matching(
            lecture = lecture.toPresentation(),
            applicants = applicants
        )
        is Lecture.Ongoing -> CoachLectureModel.Scheduled(
            lecture = lecture.toPresentation(),
            player = player
        )
        is Lecture.Done -> CoachLectureModel.Completed(
            lecture = lecture.toPresentation(),
            player = player,
            isReviewed = isReviewed
        )
    }
