package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName
import kr.co.knowledgerally.data.model.BallHistoryEntity

data class BallHistoryListResponse(
    @SerializedName("data")
    val data: List<BallHistoryResponse>
)

data class BallHistoryResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("historyDate")
    val date: String
) {

    fun toData() = BallHistoryEntity(
        title = title,
        content = content,
        count = count,
        date = date
    )
}
