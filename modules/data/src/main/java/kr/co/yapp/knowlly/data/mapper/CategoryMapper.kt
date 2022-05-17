package kr.co.yapp.knowlly.data.mapper

import kr.co.yapp.knowlly.data.model.CategoryData
import kr.co.yapp.knowlly.domain.model.Category

internal fun CategoryData.toDomain() = Category(id, name, imageResourceId, type.toDomain())

internal fun CategoryData.Type.toDomain() = Category.Type.valueOf(this.toString())
