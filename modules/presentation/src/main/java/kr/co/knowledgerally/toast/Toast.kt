package kr.co.knowledgerally.toast

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import kr.co.knowledgerally.ui.R

interface Toast {

    fun show(message: String)

    fun show(messageResId: Int)
}

object EmptyToast : Toast {
    override fun show(message: String) = Unit
    override fun show(messageResId: Int) = Unit
}

class AndroidToast(
    private val context: Context,
) : Toast {
    private var toast: android.widget.Toast? = null
    private val layoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun show(message: String) {
        toast?.cancel()
        toast = android.widget.Toast(context).apply {
            setGravity(Gravity.FILL_HORIZONTAL or Gravity.BOTTOM, 0, 0)
            duration = android.widget.Toast.LENGTH_LONG
            view = layoutInflater.inflate(R.layout.view_toast, null, false).apply {
                findViewById<TextView>(R.id.text_view).text = message
            }
        }
        toast?.show()
    }

    override fun show(messageResId: Int) {
        val message = context.getString(messageResId)
        show(message)
    }

    private fun dismiss() {
        toast?.cancel()
        toast = null
    }
}
