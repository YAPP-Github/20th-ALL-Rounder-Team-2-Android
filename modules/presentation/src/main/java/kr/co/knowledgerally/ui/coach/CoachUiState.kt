package kr.co.knowledgerally.ui.coach

import kr.co.knowledgerally.domain.model.LectureBundle

data class CoachUiState(
    val isInit: Boolean = false,
    val isLoading: Boolean = true,
    val matchingLectures: List<CoachLectureUiState.Matching> = emptyList(),
    val scheduledLectures: List<CoachLectureUiState.Scheduled> = emptyList(),
    val completedLectures: List<CoachLectureUiState.Completed> = emptyList(),
) {
    val isEmpty: Boolean
        get() = matchingLectures.isEmpty() && scheduledLectures.isEmpty() && completedLectures.isEmpty()

    fun init() = copy(isInit = true, isLoading = false)

    fun loading(isLoading: Boolean) = copy(isLoading = isLoading)

    fun from(bundle: LectureBundle?): CoachUiState {
        if (bundle == null) {
            return copy(isLoading = false)
        }
        return copy(
            isLoading = false,
            matchingLectures = bundle.onboardingLectures.map { it.toCoachUiState() as CoachLectureUiState.Matching },
            scheduledLectures = bundle.ongoingLectures.map { it.toCoachUiState() as CoachLectureUiState.Scheduled },
            completedLectures = bundle.doneLectures.map { it.toCoachUiState() as CoachLectureUiState.Completed },
        )
    }
}
