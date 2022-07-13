package kr.co.knowledgerally.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kr.co.knowledgerally.domain.model.Category
import kr.co.knowledgerally.ui.R

enum class CategoryModel(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
) {
    PM(
        R.drawable.ic_pm,
        R.string.category_service_planning
    ),
    Design(
        R.drawable.ic_design,
        R.string.category_design
    ),
    Develop(
        R.drawable.ic_develop,
        R.string.category_develop
    ),
    Marketing(
        R.drawable.ic_marketing,
        R.string.category_marketing
    ),
    Language(
        R.drawable.ic_language,
        R.string.category_language
    ),
    Etc(
        R.drawable.ic_etc,
        R.string.category_etc
    );

    companion object {
        fun from(ordinal: Int) = values().find { it.ordinal == ordinal }
    }
}

fun Category.toPresentation(): CategoryModel = when (this) {
    Category.PM -> CategoryModel.PM
    Category.Design -> CategoryModel.Design
    Category.Develop -> CategoryModel.Develop
    Category.Marketing -> CategoryModel.Marketing
    Category.Language -> CategoryModel.Language
    Category.Etc -> CategoryModel.Etc
}

fun CategoryModel.toDomain(): Category = when (this) {
    CategoryModel.PM -> Category.PM
    CategoryModel.Design -> Category.Design
    CategoryModel.Develop -> Category.Develop
    CategoryModel.Marketing -> Category.Marketing
    CategoryModel.Language -> Category.Language
    CategoryModel.Etc -> Category.Etc
}
