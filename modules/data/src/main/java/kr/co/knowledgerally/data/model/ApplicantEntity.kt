package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.Applicant
import java.time.LocalDate

data class ApplicantEntity(
    val id: String,
    val name: String,
    val content: String,
    val imageUrl: String?,
    val startAt: String,
)

internal fun ApplicantEntity.toDomain() = Applicant(
    id = id,
    name = name,
    content = content,
    imageUrl = imageUrl,
    startAt = LocalDate.parse(startAt)
)
