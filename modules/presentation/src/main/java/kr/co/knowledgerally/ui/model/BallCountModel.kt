package kr.co.knowledgerally.ui.model

import kr.co.knowledgerally.domain.model.BallCount

@JvmInline
value class BallCountModel(val value: String)

fun BallCount.toPresentation(): BallCountModel = BallCountModel(value.toString())