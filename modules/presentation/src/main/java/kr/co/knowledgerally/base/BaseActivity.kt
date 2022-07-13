package kr.co.knowledgerally.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.knowledgerally.log.Logger
import kr.co.knowledgerally.toast.Toaster
import kr.co.knowledgerally.ui.R

abstract class BaseActivity : AppCompatActivity() {

    protected inline val TAG get() = this::class.java.simpleName
    protected open val activityTransition = ActivityTransition.Push

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityTransition.overridePendingTransition(this)
    }

    protected open fun handleException(throwable: Throwable) {
        Toaster.show(R.string.exception_common)
        Logger.e(TAG, throwable)
        finish()
    }

    override fun finish() {
        super.finish()
        activityTransition.overridePendingPopTransition(this)
    }
}
