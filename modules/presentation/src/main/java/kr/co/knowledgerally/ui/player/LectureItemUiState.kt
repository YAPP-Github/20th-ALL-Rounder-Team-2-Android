package kr.co.knowledgerally.ui.player

import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.model.User

sealed interface LectureItemUiState {

    data class Matching(
        val title: String,
        val imageUrl: String?,
        val lectureInfoId: Long,
        val lecture: Lecture.Onboard,
    ) : LectureItemUiState

    data class Scheduled(
        val title: String,
        val imageUrl: String?,
        val lectureInfoId: Long,
        val lecture: Lecture.Ongoing,
        val coach: User,
    ) : LectureItemUiState

    data class Completed(
        val title: String,
        val imageUrl: String?,
        val lectureInfoId: Long,
        val lecture: Lecture.Done,
    ) : LectureItemUiState
}

fun Lecture.toUiState(
    title: String,
    imageUrl: String?,
    lectureInfoId: Long,
    coach: User,
) = when (this) {
    is Lecture.Onboard -> LectureItemUiState.Matching(
        title = title,
        imageUrl = imageUrl,
        lectureInfoId = lectureInfoId,
        lecture = this
    )
    is Lecture.Ongoing -> LectureItemUiState.Scheduled(
        title = title,
        lectureInfoId = lectureInfoId,
        imageUrl = imageUrl,
        lecture = this,
        coach = coach,
    )
    is Lecture.Done -> LectureItemUiState.Completed(
        title = title,
        lectureInfoId = lectureInfoId,
        imageUrl = imageUrl,
        lecture = this
    )
}
