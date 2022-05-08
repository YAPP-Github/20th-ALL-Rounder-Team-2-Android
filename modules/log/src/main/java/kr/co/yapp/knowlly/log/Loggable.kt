package kr.co.yapp.knowlly.log

interface Loggable {
    fun v(tag: String, msg: String)
    fun d(tag: String, msg: String)
    fun e(tag: String, msg: String)
    fun e(tag: String, throwable: Throwable?)
    fun w(tag: String, msg: String)
    fun i(tag: String, msg: String)
}
