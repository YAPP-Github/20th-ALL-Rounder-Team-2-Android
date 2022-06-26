package kr.co.knowledgerally.core.exception

class ImageException(
    uri: String,
    cause: Throwable?
) : Throwable(message = uri, cause = cause)
