package kr.co.knowledgerally.domain.model

sealed interface Lecture {

    data class Onboard(
        val id: Long,
        val schedule: Schedule,
        val applicants: List<Applicant>,
    ) : Lecture

    data class Ongoing(
        val id: Long,
        val schedule: Schedule,
        val player: User,
    ) : Lecture

    data class Done(
        val id: Long,
        val schedule: Schedule,
        val player: User,
        val isReviewed: Boolean,
    ) : Lecture
}
