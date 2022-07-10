package kr.co.knowledgerally.ui.player

import kr.co.knowledgerally.domain.model.LectureBundle

data class PlayerUiState(
    val isInit: Boolean = false,
    val isLoading: Boolean = true,
    val matchingLectures: List<PlayerLectureUiState.Matching> = emptyList(),
    val scheduledLectures: List<PlayerLectureUiState.Scheduled> = emptyList(),
    val completedLectures: List<PlayerLectureUiState.Completed> = emptyList(),
) {
    fun loading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun from(bundle: LectureBundle?): PlayerUiState {
        if (bundle == null) {
            return copy(isLoading = false)
        }
        return copy(
            isInit = true,
            isLoading = false,
            matchingLectures = bundle.onboardingLectures.map { it.toPlayerUiState() as PlayerLectureUiState.Matching },
            scheduledLectures = bundle.ongoingLectures.map { it.toPlayerUiState() as PlayerLectureUiState.Scheduled },
            completedLectures = bundle.doneLectures.map { it.toPlayerUiState() as PlayerLectureUiState.Completed },
        )
    }
}
