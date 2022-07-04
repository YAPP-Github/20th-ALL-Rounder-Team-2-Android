package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.ProviderTokenEntity

data class ProviderTokenRequest(
    @SerializedName("providerAccessToken")
    val accessToken: String,
    @SerializedName("providerName")
    val name: String,
)

fun ProviderTokenEntity.toRemote() = ProviderTokenRequest(
    accessToken = accessToken,
    name = name,
)
