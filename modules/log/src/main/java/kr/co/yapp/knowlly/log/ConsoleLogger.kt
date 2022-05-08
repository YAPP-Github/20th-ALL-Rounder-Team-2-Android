package kr.co.yapp.knowlly.log

import kr.co.yapp.knowlly.log.Logger.Level

object ConsoleLogger : Loggable {

    override fun v(tag: String, msg: String) {
        println("[${Level.VERBOSE.name}] $tag: $msg")
    }

    override fun d(tag: String, msg: String) {
        println("[${Level.DEBUG.name}] $tag: $msg")
    }

    override fun e(tag: String, msg: String) {
        System.err.println("[${Level.ERROR.name}] $tag: $msg")
    }

    override fun e(tag: String, throwable: Throwable?) {
        System.err.println("[${Level.ERROR.name}] $tag: ${throwable?.toString()}")
    }

    override fun w(tag: String, msg: String) {
        println("[${Level.WARNING.name}] $tag: $msg")
    }

    override fun i(tag: String, msg: String) {
        println("[${Level.INFO.name}] $tag: $msg")
    }
}
