package kr.co.knowledgerally.ui.lesson.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kr.co.knowledgerally.navigation.NavigationDestination
import kr.co.knowledgerally.ui.lesson.LessonScreen

object LessonDestination : NavigationDestination {
    override val route: String = "lesson"
}

fun NavGraphBuilder.lessonGraph() {
    composable(LessonDestination.route) {
        LessonScreen()
    }
}
