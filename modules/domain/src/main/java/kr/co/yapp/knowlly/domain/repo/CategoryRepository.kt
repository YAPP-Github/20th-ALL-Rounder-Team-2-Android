package kr.co.yapp.knowlly.domain.repo

import kr.co.yapp.knowlly.domain.model.Category

interface CategoryRepository {

    suspend fun getCategoryList(): List<Category>
}
