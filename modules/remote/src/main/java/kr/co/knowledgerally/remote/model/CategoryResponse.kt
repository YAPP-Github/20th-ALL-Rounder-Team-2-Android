package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.CategoryEntity

enum class CategoryResponse {
    @SerializedName("PM")
    PM,

    @SerializedName("DESIGN")
    Design,

    @SerializedName("DEVELOP")
    Develop,

    @SerializedName("MARKETING")
    Marketing,

    @SerializedName("LANGUAGE")
    Language,

    @SerializedName("ETC")
    Etc,
}

fun CategoryEntity.toRemote(): CategoryResponse = when (this) {
    CategoryEntity.PM -> CategoryResponse.PM
    CategoryEntity.Design -> CategoryResponse.Design
    CategoryEntity.Develop -> CategoryResponse.Develop
    CategoryEntity.Marketing -> CategoryResponse.Marketing
    CategoryEntity.Language -> CategoryResponse.Language
    CategoryEntity.Etc -> CategoryResponse.Etc
}

fun CategoryResponse.toData(): CategoryEntity = when (this) {
    CategoryResponse.PM -> CategoryEntity.PM
    CategoryResponse.Design -> CategoryEntity.Design
    CategoryResponse.Develop -> CategoryEntity.Develop
    CategoryResponse.Marketing -> CategoryEntity.Marketing
    CategoryResponse.Language -> CategoryEntity.Language
    CategoryResponse.Etc -> CategoryEntity.Etc
}
