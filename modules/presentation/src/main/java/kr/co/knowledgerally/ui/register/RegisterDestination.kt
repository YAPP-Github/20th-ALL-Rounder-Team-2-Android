package kr.co.knowledgerally.ui.register

sealed interface RegisterDestination {

    val route: String

    fun buildRoute(): String

    object Lounge : RegisterDestination {
        override val route: String
            get() = "register/lounge"

        override fun buildRoute(): String = route
    }

    data class Info(
        val isRoot: Boolean
    ) : RegisterDestination {

        override val route: String
            get() = "register/info/{isRoot}"

        override fun buildRoute(): String = "register/info/$isRoot"

        companion object {
            val Default = Info(false)
        }
    }

    object Schedule : RegisterDestination {
        override val route: String
            get() = "register/schedule/{lectureId}"

        override fun buildRoute(): String = "register/schedule"
    }
}
