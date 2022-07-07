package kr.co.knowledgerally.domain.model

sealed interface Lecture {

    data class Onboard(
        val lecture: LectureInfo,
        val coach: User,
        val applicants: List<Applicant>
    ) : Lecture

    data class Ongoing(
        val lecture: LectureInfo,
        val coach: User,
        val player: User
    ) : Lecture

    data class Done(
        val lecture: LectureInfo,
        val coach: User,
        val player: User,
        val isReviewed: Boolean
    ) : Lecture
}
