package kr.co.knowledgerally.domain.model

data class Registration(
    val category: Category,
    val name: String,
    val introduce: String,
    val tags: List<String>,
    val imageUris: List<String>,
)
