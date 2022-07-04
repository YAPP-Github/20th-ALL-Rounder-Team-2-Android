package kr.co.knowledgerally.core.exception

class ImageTranscodeException(
    uri: String,
    cause: Throwable?
) : Throwable(message = uri, cause = cause)
