package kr.co.knowledgerally.ui.coach

import kr.co.knowledgerally.domain.model.Applicant
import kr.co.knowledgerally.domain.model.Lesson

sealed interface ClassUiState {

    data class Matching(
        val classId: String,
        val className: String,
        val thumbnailUrl: String?,
        val applicants: List<Applicant>,
    ) : ClassUiState

    data class Scheduled(
        val classId: String,
        val className: String,
        val thumbnailUrl: String?,
        val playerName: String,
        val playerKakaoId: String,
        val startTime: String,
        val runningTime: String
    ) : ClassUiState

    data class Completed(
        val classId: String,
        val className: String,
        val thumbnailUrl: String?,
        val playerName: String,
        val startTime: String,
        val runningTime: String,
        val isReviewed: Boolean
    ) : ClassUiState
}

fun Lesson.toCoachPresentation() =
    when (type) {
        Lesson.Type.Matching -> {
            ClassUiState.Matching(
                classId = id.toString(),
                className = title,
                thumbnailUrl = thumbnailUrl,
                applicants = applicants!!
            )
        }
        Lesson.Type.Scheduled -> {
            ClassUiState.Scheduled(
                classId = id.toString(),
                className = title,
                thumbnailUrl = thumbnailUrl,
                playerName = playerName!!,
                playerKakaoId = playerKakaoId!!,
                startTime = startAt.toString(),
                runningTime = "3시간" // LocalDateTime 계산 관련 함수가 거의 대부분 API 26 이상을 필요
            )
        }
        Lesson.Type.Completed -> {
            ClassUiState.Completed(
                classId = id.toString(),
                className = title,
                thumbnailUrl = thumbnailUrl,
                playerName = playerName!!,
                startTime = startAt.toString(),
                runningTime = "3시간",
                isReviewed = isReviewed
            )
        }
    }
