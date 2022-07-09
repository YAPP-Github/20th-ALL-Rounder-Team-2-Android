package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.Lecture

sealed class LectureEntity {

    data class Onboard(
        val lecture: LectureInfoEntity,
        val coach: UserEntity,
        val applicants: List<ApplicantEntity>
    ) : LectureEntity()

    data class Ongoing(
        val lecture: LectureInfoEntity,
        val coach: UserEntity,
        val player: UserEntity
    ) : LectureEntity()

    data class Done(
        val lecture: LectureInfoEntity,
        val coach: UserEntity,
        val player: UserEntity,
        val isReviewed: Boolean
    ) : LectureEntity()
}

internal fun LectureEntity.toDomain(): Lecture =
    when (this) {
        is LectureEntity.Onboard -> {
            Lecture.Onboard(
                lecture = lecture.toDomain(),
                coach = coach.toDomain(),
                applicants = applicants.map { it.toDomain() }
            )
        }
        is LectureEntity.Ongoing -> {
            Lecture.Ongoing(
                lecture = lecture.toDomain(),
                coach = coach.toDomain(),
                player = player.toDomain()
            )
        }
        is LectureEntity.Done -> {
            Lecture.Done(
                lecture = lecture.toDomain(),
                coach = coach.toDomain(),
                player = player.toDomain(),
                isReviewed = isReviewed
            )
        }
    }
