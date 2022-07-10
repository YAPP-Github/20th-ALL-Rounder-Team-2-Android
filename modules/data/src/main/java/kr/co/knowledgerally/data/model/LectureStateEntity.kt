package kr.co.knowledgerally.data.model

import kr.co.knowledgerally.domain.model.LectureState

enum class LectureStateEntity { Onboard, Ongoing, Done }

internal fun LectureState.toData() = when (this) {
    LectureState.Onboard -> LectureStateEntity.Onboard
    LectureState.Ongoing -> LectureStateEntity.Ongoing
    LectureState.Done -> LectureStateEntity.Done
}
