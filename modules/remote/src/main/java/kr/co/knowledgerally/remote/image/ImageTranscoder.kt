package kr.co.knowledgerally.remote.image

interface ImageTranscoder {

    fun from(uri: String): Result<ByteArray>
}
