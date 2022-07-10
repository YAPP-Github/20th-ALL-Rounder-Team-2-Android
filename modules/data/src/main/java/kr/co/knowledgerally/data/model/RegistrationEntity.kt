package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.Registration

data class RegistrationEntity(
    val category: CategoryEntity,
    val topic: String,
    val introduce: String,
    val tags: List<String>,
    val imageUris: List<String>,
)

fun Registration.toData() = RegistrationEntity(
    category = category.toData(),
    topic = topic,
    introduce = introduce,
    tags = tags,
    imageUris = imageUris
)
