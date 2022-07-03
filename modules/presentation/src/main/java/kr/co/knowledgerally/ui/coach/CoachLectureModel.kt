package kr.co.knowledgerally.ui.coach

import kr.co.knowledgerally.domain.model.Applicant
import kr.co.knowledgerally.domain.model.Lecture
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

sealed interface CoachLectureModel {

    data class Matching(
        val lectureId: String,
        val lectureTitle: String,
        val applicants: List<Applicant>,
    ) : CoachLectureModel

    data class Scheduled(
        val lectureId: String,
        val lectureTitle: String,
        val playerName: String,
        val playerKakaoId: String,
        val startTime: LocalDateTime,
        val runningTime: Long
    ) : CoachLectureModel

    data class Completed(
        val lectureId: String,
        val lectureTitle: String,
        val playerName: String,
        val startTime: LocalDateTime,
        val runningTime: Long,
        val isReviewed: Boolean
    ) : CoachLectureModel
}

fun Lecture.toCoachPresentation() =
    when (type) {
        Lecture.Type.Matching -> {
            CoachLectureModel.Matching(
                lectureId = id.toString(),
                lectureTitle = title,
                applicants = applicants!!
            )
        }
        Lecture.Type.Scheduled -> {
            CoachLectureModel.Scheduled(
                lectureId = id.toString(),
                lectureTitle = title,
                playerName = playerName!!,
                playerKakaoId = playerKakaoId!!,
                startTime = startAt,
                runningTime = ChronoUnit.HOURS.between(endAt, startAt)
            )
        }
        Lecture.Type.Completed -> {
            CoachLectureModel.Completed(
                lectureId = id.toString(),
                lectureTitle = title,
                playerName = playerName!!,
                startTime = startAt,
                runningTime = ChronoUnit.HOURS.between(endAt, startAt),
                isReviewed = isReviewed
            )
        }
    }
