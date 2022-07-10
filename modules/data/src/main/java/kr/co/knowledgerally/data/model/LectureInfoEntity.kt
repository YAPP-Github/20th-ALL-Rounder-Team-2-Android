package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.LectureInfo

data class LectureInfoEntity(
    val id: Long,
    val lectures: List<LectureEntity>,
    val topic: String,
    val imageUrls: List<String>,
    val coach: UserEntity,
)

internal fun LectureInfoEntity.toDomain() = LectureInfo(
    id = id,
    lectures = lectures.map { it.toDomain() },
    topic = topic,
    imageUrls = imageUrls,
    coach = coach.toDomain(),
)
