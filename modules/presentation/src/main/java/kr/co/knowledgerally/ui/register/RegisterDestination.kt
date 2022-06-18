package kr.co.knowledgerally.ui.register

private const val ROUTE_REGISTER = "register"
private const val ROUTE_SCHEDULE = "schedule"

enum class RegisterDestination(
    val route: String,
) {
    Register(ROUTE_REGISTER),
    Schedule(ROUTE_SCHEDULE),
}
