package kr.co.knowledgerally.remote.image

class Image(
    val data: ByteArray,
    val filename: String,
) {

    override fun toString(): String = "Image(filename=$filename)"
}
