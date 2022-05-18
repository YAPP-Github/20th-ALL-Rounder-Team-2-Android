package kr.co.knowledgerally.log

import android.util.Log

object AndroidLogger : Loggable {

    override fun v(tag: String, msg: String) {
        Log.v(tag, msg)
    }

    override fun i(tag: String, msg: String) {
        Log.i(tag, msg)
    }

    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    override fun w(tag: String, msg: String) {
        Log.w(tag, msg)
    }

    override fun e(tag: String, msg: String) {
        Log.e(tag, msg)
    }

    override fun e(tag: String, throwable: Throwable?) {
        val log = Log.getStackTraceString(throwable)
        if (log.isEmpty()) {
            Log.e(tag, throwable?.toString(), throwable)
        } else {
            e(tag, log)
        }
    }
}
