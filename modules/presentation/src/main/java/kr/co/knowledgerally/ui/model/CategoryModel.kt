package kr.co.knowledgerally.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kr.co.knowledgerally.ui.R

enum class CategoryModel(
    @DrawableRes val drawableResId: Int,
    @StringRes val textResId: Int,
) {
    ServicePlanning(
        R.drawable.ic_service_planning,
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
    )
}
