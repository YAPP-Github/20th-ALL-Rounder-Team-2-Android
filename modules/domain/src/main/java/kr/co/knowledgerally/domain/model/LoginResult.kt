package kr.co.knowledgerally.domain.model

sealed interface LoginResult {

    object Signed : LoginResult

    object Onboard : LoginResult

    data class NotSigned(
        val providerToken: ProviderToken
    ) : LoginResult
}
