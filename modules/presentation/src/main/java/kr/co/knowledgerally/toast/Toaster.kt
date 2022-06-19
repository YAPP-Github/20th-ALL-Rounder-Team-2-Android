package kr.co.knowledgerally.toast

object Toaster {
    private var toast: Toast = EmptyToast

    fun init(toast: Toast) {
        this.toast = toast
    }

    fun show(message: String) = toast.show(message)

    fun show(messageResId: Int) = toast.show(messageResId)
}
