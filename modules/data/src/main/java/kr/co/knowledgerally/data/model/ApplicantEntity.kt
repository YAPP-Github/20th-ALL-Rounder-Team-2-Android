package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.Applicant
import java.time.LocalDate
import java.time.LocalDateTime

data class ApplicantEntity(
    val id: Long,
    val name: String,
    val content: String,
    val imageUrl: String?,
    val startAt: LocalDateTime,
)

internal fun ApplicantEntity.toDomain() = Applicant(
    id = id,
    name = name,
    content = content,
    imageUrl = imageUrl,
    startAt = startAt
)
