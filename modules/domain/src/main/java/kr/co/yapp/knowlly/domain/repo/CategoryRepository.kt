package kr.co.yapp.knowlly.domain.repo

import kr.co.yapp.knowlly.domain.model.Category

interface CategoryRepository {

    suspend fun getCategoryList(): Result<List<Category>>
}
