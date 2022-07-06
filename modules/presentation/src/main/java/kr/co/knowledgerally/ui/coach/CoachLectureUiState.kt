package kr.co.knowledgerally.ui.coach

import kr.co.knowledgerally.domain.model.Applicant
import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.model.LectureInfo
import kr.co.knowledgerally.domain.model.User

sealed interface CoachLectureUiState {

    data class Matching(
        val lecture: LectureInfo,
        val applicants: List<Applicant>
    ) : CoachLectureUiState

    data class Scheduled(
        val lecture: LectureInfo,
        val player: User
    ) : CoachLectureUiState

    data class Completed(
        val lecture: LectureInfo,
        val player: User,
        val isReviewed: Boolean
    ) : CoachLectureUiState
}

fun Lecture.toCoachUiState(): CoachLectureUiState =
    when (this) {
        is Lecture.Onboard -> CoachLectureUiState.Matching(
            lecture = lecture,
            applicants = applicants
        )
        is Lecture.Ongoing -> CoachLectureUiState.Scheduled(
            lecture = lecture,
            player = player
        )
        is Lecture.Done -> CoachLectureUiState.Completed(
            lecture = lecture,
            player = player,
            isReviewed = isReviewed
        )
    }
