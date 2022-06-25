package kr.co.knowledgerally.remote.model

data class OnboardedResponse(
    @SerializedName("data")
    val data: Data
) {

    data class Data(
        @SerializedName("onboarded")
        val isOnboarded: Boolean
    )
}
