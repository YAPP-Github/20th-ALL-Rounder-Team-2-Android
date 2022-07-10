package kr.co.knowledgerally.ui.player

import kr.co.knowledgerally.domain.model.LectureLegacy
import kr.co.knowledgerally.domain.model.LectureInfoLegacy
import kr.co.knowledgerally.domain.model.User

sealed interface PlayerLectureUiState {

    val lecture: LectureInfoLegacy
    val coach: User

    data class Matching(
        override val lecture: LectureInfoLegacy,
        override val coach: User
    ) : PlayerLectureUiState

    data class Scheduled(
        override val lecture: LectureInfoLegacy,
        override val coach: User
    ) : PlayerLectureUiState

    data class Completed(
        override val lecture: LectureInfoLegacy,
        override val coach: User,
        val isReviewed: Boolean
    ) : PlayerLectureUiState
}

fun LectureLegacy.toPlayerUiState(): PlayerLectureUiState =
    when (this) {
        is LectureLegacy.Onboard -> PlayerLectureUiState.Matching(
            lecture = lecture,
            coach = coach
        )
        is LectureLegacy.Ongoing -> PlayerLectureUiState.Scheduled(
            lecture = lecture,
            coach = coach
        )
        is LectureLegacy.Done -> PlayerLectureUiState.Completed(
            lecture = lecture,
            coach = coach,
            isReviewed = isReviewed
        )
    }
