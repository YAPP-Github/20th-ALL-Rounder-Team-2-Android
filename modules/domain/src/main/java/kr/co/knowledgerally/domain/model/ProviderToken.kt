package kr.co.knowledgerally.domain.model

data class ProviderToken(
    val accessToken: String,
    val name: String,
) {

    companion object {
        private const val NAME_KAKAO = "KAKAO"

        fun kakao(accessToken: String) = ProviderToken(
            accessToken = accessToken,
            name = NAME_KAKAO
        )
    }
}
