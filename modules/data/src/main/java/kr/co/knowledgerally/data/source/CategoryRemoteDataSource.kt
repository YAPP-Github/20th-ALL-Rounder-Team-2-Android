package kr.co.knowledgerally.data.source

import kr.co.knowledgerally.data.model.CategoryEntity

interface CategoryRemoteDataSource {

    suspend fun getCategoryList(): Result<List<CategoryEntity>>
}
