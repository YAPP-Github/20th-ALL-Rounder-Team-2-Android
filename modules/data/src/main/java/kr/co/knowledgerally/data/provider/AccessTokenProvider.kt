package kr.co.knowledgerally.data.provider

interface AccessTokenProvider : Provider<String> {
    override fun get(): String
}
