package kr.co.knowledgerally.domain.model

data class Applicant(
    val id: Long,
    val name: String,
    val content: String,
    val imageUrl: String?,
    val schedule: Schedule,
)
