package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class CategoryResponseWrapper(
    @SerializedName("data")
    val data: List<Data>,
) {

    data class Data(
        @SerializedName("categoryName")
        val category: CategoryResponse
    )
}
