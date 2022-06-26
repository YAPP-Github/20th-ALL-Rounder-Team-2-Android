package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class BallHistoryResponseWrapper(
    @SerializedName("data")
    val data: List<BallHistoryResponse>
)