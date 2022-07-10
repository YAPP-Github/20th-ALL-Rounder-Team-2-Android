package kr.co.knowledgerally.ui.coach

import kr.co.knowledgerally.domain.model.Applicant
import kr.co.knowledgerally.domain.model.LectureLegacy
import kr.co.knowledgerally.domain.model.LectureInfoLegacy
import kr.co.knowledgerally.domain.model.User

sealed interface CoachLectureUiState {

    data class Matching(
        val lecture: LectureInfoLegacy,
        val applicants: List<Applicant>
    ) : CoachLectureUiState

    data class Scheduled(
        val lecture: LectureInfoLegacy,
        val player: User
    ) : CoachLectureUiState

    data class Completed(
        val lecture: LectureInfoLegacy,
        val player: User,
        val isReviewed: Boolean
    ) : CoachLectureUiState
}

fun LectureLegacy.toCoachUiState(): CoachLectureUiState =
    when (this) {
        is LectureLegacy.Onboard -> CoachLectureUiState.Matching(
            lecture = lecture,
            applicants = applicants
        )
        is LectureLegacy.Ongoing -> CoachLectureUiState.Scheduled(
            lecture = lecture,
            player = player
        )
        is LectureLegacy.Done -> CoachLectureUiState.Completed(
            lecture = lecture,
            player = player,
            isReviewed = isReviewed
        )
    }
