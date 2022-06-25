package kr.co.knowledgerally.data.provider

interface Provider<T> {
    fun get(): T
}
