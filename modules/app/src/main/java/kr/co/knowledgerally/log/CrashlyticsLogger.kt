package kr.co.knowledgerally.log

import com.google.firebase.crashlytics.FirebaseCrashlytics

object CrashlyticsLogger : Loggable {

    override fun v(tag: String, msg: String) = Unit

    override fun d(tag: String, msg: String) = Unit

    override fun e(tag: String, msg: String) {
        FirebaseCrashlytics.getInstance().log(msg)
    }

    override fun e(tag: String, throwable: Throwable?) {
        if (throwable != null) {
            FirebaseCrashlytics.getInstance().recordException(throwable)
        }
    }

    override fun w(tag: String, msg: String) = Unit

    override fun i(tag: String, msg: String) = Unit
}
