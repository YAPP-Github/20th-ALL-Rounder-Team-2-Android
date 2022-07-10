package kr.co.knowledgerally.ui.coach

import kr.co.knowledgerally.domain.model.Lecture

sealed interface LectureItemUiState {

    data class Matching(
        val title: String,
        val lectureInfoId: Long,
        val lecture: Lecture.Onboard,
    ) : LectureItemUiState

    data class Scheduled(
        val title: String,
        val lectureInfoId: Long,
        val lecture: Lecture.Ongoing,
    ) : LectureItemUiState

    data class Completed(
        val title: String,
        val lectureInfoId: Long,
        val lecture: Lecture.Done,
    ) : LectureItemUiState
}

fun Lecture.toUiState(title: String, lectureInfoId: Long) = when (this) {
    is Lecture.Onboard -> LectureItemUiState.Matching(
        title = title,
        lectureInfoId = lectureInfoId,
        lecture = this
    )
    is Lecture.Ongoing -> LectureItemUiState.Scheduled(
        title = title,
        lectureInfoId = lectureInfoId,
        lecture = this
    )
    is Lecture.Done -> LectureItemUiState.Completed(
        title = title,
        lectureInfoId = lectureInfoId,
        lecture = this
    )
}
