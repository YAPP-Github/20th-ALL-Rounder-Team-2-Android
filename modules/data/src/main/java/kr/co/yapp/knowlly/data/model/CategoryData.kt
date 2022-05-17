package kr.co.yapp.knowlly.data.model

data class CategoryData(
    val id: Long,
    val name: String,
    val imageResourceId: Int,
    val type: Type
) {

    enum class Type {
        Knowledge, Hobby
    }
}
