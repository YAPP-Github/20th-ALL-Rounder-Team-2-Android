package kr.co.knowledgerally.ui.player

import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.model.LectureInfo
import kr.co.knowledgerally.domain.model.User

sealed interface PlayerLectureUiState {

    val lecture: LectureInfo
    val coach: User

    data class Matching(
        override val lecture: LectureInfo,
        override val coach: User
    ) : PlayerLectureUiState

    data class Scheduled(
        override val lecture: LectureInfo,
        override val coach: User
    ) : PlayerLectureUiState

    data class Completed(
        override val lecture: LectureInfo,
        override val coach: User,
        val isReviewed: Boolean
    ) : PlayerLectureUiState
}

fun Lecture.toPlayerUiState(): PlayerLectureUiState =
    when (this) {
        is Lecture.Onboard -> PlayerLectureUiState.Matching(
            lecture = lecture,
            coach = coach
        )
        is Lecture.Ongoing -> PlayerLectureUiState.Scheduled(
            lecture = lecture,
            coach = coach
        )
        is Lecture.Done -> PlayerLectureUiState.Completed(
            lecture = lecture,
            coach = coach,
            isReviewed = isReviewed
        )
    }
