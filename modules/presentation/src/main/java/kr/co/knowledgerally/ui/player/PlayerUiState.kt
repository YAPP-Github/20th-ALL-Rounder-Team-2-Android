package kr.co.knowledgerally.ui.player

import kr.co.knowledgerally.domain.model.LectureInfo

data class PlayerUiState(
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

    fun loading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun from(lectureInfoList: List<LectureInfo>): PlayerUiState = copy(
        isInit = true,
        isLoading = false,
        lectureItems = lectureInfoList.flatMap { lectureInfo ->
            lectureInfo.lectures.map { lecture ->
                lecture.toUiState(
                    title = lectureInfo.topic,
                    imageUrl = lectureInfo.thumbnailImageUrl,
                    lectureInfoId = lectureInfo.id,
                    coach = lectureInfo.coach,
                )
            }
        }
    )
}
