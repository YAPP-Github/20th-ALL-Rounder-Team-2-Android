package kr.co.knowledgerally.ui.lecture

enum class LectureType(
    val queryString: String
) {
    Player(queryString = "player"),
    Coach(queryString = "coach")
}
