package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.LectureLegacy

sealed class LectureEntityLegacy {

    data class Onboard(
        val lecture: LectureInfoEntityLegacy,
        val coach: UserEntity,
        val applicants: List<ApplicantEntity>
    ) : LectureEntityLegacy()

    data class Ongoing(
        val lecture: LectureInfoEntityLegacy,
        val coach: UserEntity,
        val player: UserEntity
    ) : LectureEntityLegacy()

    data class Done(
        val lecture: LectureInfoEntityLegacy,
        val coach: UserEntity,
        val player: UserEntity,
        val isReviewed: Boolean
    ) : LectureEntityLegacy()
}

internal fun LectureEntityLegacy.toDomain(): LectureLegacy =
    when (this) {
        is LectureEntityLegacy.Onboard -> {
            LectureLegacy.Onboard(
                lecture = lecture.toDomain(),
                coach = coach.toDomain(),
                applicants = applicants.map { it.toDomain() }
            )
        }
        is LectureEntityLegacy.Ongoing -> {
            LectureLegacy.Ongoing(
                lecture = lecture.toDomain(),
                coach = coach.toDomain(),
                player = player.toDomain()
            )
        }
        is LectureEntityLegacy.Done -> {
            LectureLegacy.Done(
                lecture = lecture.toDomain(),
                coach = coach.toDomain(),
                player = player.toDomain(),
                isReviewed = isReviewed
            )
        }
    }
