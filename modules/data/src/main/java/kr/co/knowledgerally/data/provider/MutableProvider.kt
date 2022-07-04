package kr.co.knowledgerally.data.provider

interface MutableProvider<T> : Provider<T> {
    override var value: T
}
