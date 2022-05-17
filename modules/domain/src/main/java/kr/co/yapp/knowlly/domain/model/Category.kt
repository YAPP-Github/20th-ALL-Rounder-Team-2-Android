package kr.co.yapp.knowlly.domain.model

data class Category(
    val id: Long,
    val name: String,
    val imageResourceId: Int,
    val type: Type
) {

    enum class Type {
        Knowledge, Hobby
    }
}
