package kr.co.knowledgerally.domain.model

data class JwtToken(
    val accessToken: String,
    val refreshToken: String,
) {
    val isEmpty: Boolean get() = this == Empty
    val isNotEmpty: Boolean get() = !isEmpty

    companion object {
        val Empty = JwtToken("", "")
    }
}
