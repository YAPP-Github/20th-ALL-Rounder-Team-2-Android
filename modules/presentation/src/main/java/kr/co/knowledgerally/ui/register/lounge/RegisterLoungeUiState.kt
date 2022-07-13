package kr.co.knowledgerally.ui.register.lounge

import kr.co.knowledgerally.domain.model.LectureInfo

sealed interface RegisterLoungeUiState {

    object Loading : RegisterLoungeUiState

    object NoLecture : RegisterLoungeUiState

    data class Lectures(
        val lectureInfoList: List<LectureInfo>
    ) : RegisterLoungeUiState
}
