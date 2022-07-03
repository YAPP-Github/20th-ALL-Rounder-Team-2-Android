package kr.co.knowledgerally.remote.image

interface ImageTranscoder {

    fun from(uriString: String): Result<Image>
}
