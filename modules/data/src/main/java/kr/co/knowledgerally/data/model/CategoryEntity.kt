package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.Category

enum class CategoryEntity {
    PM,
    Design,
    Develop,
    Marketing,
    Language,
    Etc,
}

fun Category.toData(): CategoryEntity = when (this) {
    Category.PM -> CategoryEntity.PM
    Category.Design -> CategoryEntity.Design
    Category.Develop -> CategoryEntity.Develop
    Category.Marketing -> CategoryEntity.Marketing
    Category.Language -> CategoryEntity.Language
    Category.Etc -> CategoryEntity.Etc
}

fun CategoryEntity.toDomain(): Category = when (this) {
    CategoryEntity.PM -> Category.PM
    CategoryEntity.Design -> Category.Design
    CategoryEntity.Develop -> Category.Develop
    CategoryEntity.Marketing -> Category.Marketing
    CategoryEntity.Language -> Category.Language
    CategoryEntity.Etc -> Category.Etc
}
