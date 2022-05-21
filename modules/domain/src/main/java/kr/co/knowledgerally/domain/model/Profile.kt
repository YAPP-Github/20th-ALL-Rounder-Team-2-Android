package kr.co.knowledgerally.domain.model

data class Profile(
    val nickname: String,
    val imageUrl: String?,
    val selfIntroduction: String,
    val webUrls: List<String>,
) {

    val isEmpty: Boolean get() = this == EMPTY

    companion object {
        val EMPTY = Profile(
            nickname = "",
            imageUrl = null,
            selfIntroduction = "",
            webUrls = emptyList()
        )
    }
}
