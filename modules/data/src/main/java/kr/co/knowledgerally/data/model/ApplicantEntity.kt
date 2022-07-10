package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.Applicant

data class ApplicantEntity(
    val id: Long,
    val name: String,
    val content: String,
    val imageUrl: String?,
    val schedule: ScheduleEntity,
)

internal fun ApplicantEntity.toDomain() = Applicant(
    id = id,
    name = name,
    content = content,
    imageUrl = imageUrl,
    schedule = schedule.toDomain(),
)
