package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.Lecture

sealed interface LectureEntity {

    data class Onboard(
        val id: Long,
        val schedule: ScheduleEntity,
        val applicants: List<ApplicantEntity>,
    ) : LectureEntity

    data class Ongoing(
        val id: Long,
        val schedule: ScheduleEntity,
        val player: UserEntity,
    ) : LectureEntity

    data class Done(
        val id: Long,
        val schedule: ScheduleEntity,
        val player: UserEntity,
        val isReviewed: Boolean,
    ) : LectureEntity
}

internal fun LectureEntity.toDomain(): Lecture = when (this) {
    is LectureEntity.Onboard -> Lecture.Onboard(
        id = id,
        schedule = schedule.toDomain(),
        applicants = applicants.map { it.toDomain() },
    )
    is LectureEntity.Ongoing -> Lecture.Ongoing(
        id = id,
        schedule = schedule.toDomain(),
        player = player.toDomain(),
    )
    is LectureEntity.Done -> Lecture.Done(
        id = id,
        schedule = schedule.toDomain(),
        player = player.toDomain(),
        isReviewed = isReviewed,
    )
}
