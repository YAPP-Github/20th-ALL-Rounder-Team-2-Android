package kr.co.knowledgerally.remote.model

import com.google.gson.annotations.SerializedName

data class SchedulesRequest(
    @SerializedName("schedules")
    val schedules: List<ScheduleRequest>
)
