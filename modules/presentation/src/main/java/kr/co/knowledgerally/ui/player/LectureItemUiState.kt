package kr.co.knowledgerally.ui.player

import kr.co.knowledgerally.domain.model.Lecture
import kr.co.knowledgerally.domain.model.LectureInfo

sealed interface LectureItemUiState {

    data class Matching(
        val lectureInfo: LectureInfo,
        val lecture: Lecture.Onboard,
    ) : LectureItemUiState

    data class Scheduled(
        val lectureInfo: LectureInfo,
        val lecture: Lecture.Ongoing,
    ) : LectureItemUiState

    data class Completed(
        val lectureInfo: LectureInfo,
        val lecture: Lecture.Done,
    ) : LectureItemUiState
}

fun Lecture.toUiState(lectureInfo: LectureInfo) = when (this) {
    is Lecture.Onboard ->
        LectureItemUiState.Matching(lectureInfo = lectureInfo, lecture = this)
    is Lecture.Ongoing ->
        LectureItemUiState.Scheduled(lectureInfo = lectureInfo, lecture = this)
    is Lecture.Done ->
        LectureItemUiState.Completed(lectureInfo = lectureInfo, lecture = this)
}
