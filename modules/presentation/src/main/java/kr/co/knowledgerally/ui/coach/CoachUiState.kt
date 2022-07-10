package kr.co.knowledgerally.ui.coach

import kr.co.knowledgerally.domain.model.LectureInfo

data class CoachUiState(
    val isInit: Boolean = false,
    val isLoading: Boolean = true,
    val lectureItems: List<LectureItemUiState> = emptyList(),
) {
    val matchingLectures: List<LectureItemUiState.Matching> =
        lectureItems.filterIsInstance<LectureItemUiState.Matching>()

    val scheduledLectures: List<LectureItemUiState.Scheduled> =
        lectureItems.filterIsInstance<LectureItemUiState.Scheduled>()

    val completedLectures: List<LectureItemUiState.Completed> =
        lectureItems.filterIsInstance<LectureItemUiState.Completed>()

    val isEmpty: Boolean
        get() = matchingLectures.isEmpty() && scheduledLectures.isEmpty() && completedLectures.isEmpty()

    fun init() = copy(isInit = true, isLoading = false)

    fun loading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun from(lectureInfoList: List<LectureInfo>): CoachUiState = copy(
        isInit = true,
        isLoading = false,
        lectureItems = lectureInfoList.flatMap { lectureInfo ->
            lectureInfo.lectures.map { lecture ->
                lecture.toUiState(title = lectureInfo.topic, lectureInfoId = lectureInfo.id)
            }
        }
    )
}
