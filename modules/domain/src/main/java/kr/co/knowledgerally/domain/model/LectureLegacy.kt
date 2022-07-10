package kr.co.knowledgerally.domain.model

sealed interface LectureLegacy {

    data class Onboard(
        val lecture: LectureInfoLegacy,
        val coach: User,
        val applicants: List<Applicant>
    ) : LectureLegacy {
        val lectureId = lecture.id
    }

    data class Ongoing(
        val lecture: LectureInfoLegacy,
        val coach: User,
        val player: User
    ) : LectureLegacy

    data class Done(
        val lecture: LectureInfoLegacy,
        val coach: User,
        val player: User,
        val isReviewed: Boolean
    ) : LectureLegacy
}
