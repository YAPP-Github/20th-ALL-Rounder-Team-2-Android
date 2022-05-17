package kr.co.yapp.knowlly.data.repo

import kr.co.yapp.knowlly.data.mapper.toDomain
import kr.co.yapp.knowlly.data.model.CategoryData
import kr.co.yapp.knowlly.domain.model.Category
import kr.co.yapp.knowlly.domain.repo.CategoryRepository

internal class CategoryRepositoryImpl : CategoryRepository {

    override suspend fun getCategoryList(): List<Category> {
        return categoryList.map { it.toDomain() }
    }

    companion object {

        val categoryList = listOf(
            CategoryData(
                id = 0,
                name = "외국 / 언어",
                imageResourceId = 0,
                type = CategoryData.Type.Knowledge
            ),
            CategoryData(
                id = 1,
                name = "인문 / 도서",
                imageResourceId = 0,
                type = CategoryData.Type.Hobby
            ),

            /* . . . */
        )
    }
}
