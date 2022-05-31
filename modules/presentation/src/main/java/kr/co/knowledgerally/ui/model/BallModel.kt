package kr.co.knowledgerally.ui.model

import kr.co.knowledgerally.domain.model.Ball

@JvmInline
value class BallModel(val value: String)

fun Ball.toPresentation(): BallModel = BallModel(value.toString())