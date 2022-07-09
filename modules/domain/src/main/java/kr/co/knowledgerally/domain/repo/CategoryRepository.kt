package kr.co.knowledgerally.domain.repo

import kr.co.knowledgerally.domain.model.Category

interface CategoryRepository {

    suspend fun getCategoryList(): Result<List<Category>>
}
