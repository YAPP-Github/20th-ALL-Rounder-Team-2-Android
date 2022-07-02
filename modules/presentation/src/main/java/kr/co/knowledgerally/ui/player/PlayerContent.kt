package kr.co.knowledgerally.ui.player

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.*
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun PlayerContent(
    tab: PlayerTabState.Tab,
    uiState: PlayerUiState.Success
) {
    val lessonList = when (tab) {
        PlayerTabState.Tab.Matching -> uiState.matchingLessons
        PlayerTabState.Tab.Scheduled -> uiState.scheduledLessons
        PlayerTabState.Tab.Completed -> uiState.completedLessons
    }

    if (lessonList.isNotEmpty()) {
        Column {
            PlayerContentList(lessonList = lessonList)
            KnowllyDivider()
        }
    } else {
        PlayerContentEmpty(tab = tab)
    }
}

@Composable
fun PlayerContentList(
    lessonList: List<PlayerLessonModel>
) {
    LazyColumn {
        items(lessonList) { lesson ->
            PlayerContentListItem(lesson = lesson)
        }
    }
}

@Composable
fun PlayerContentListItem(
    lesson: PlayerLessonModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        KnowllyDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = KnowllyTheme.colors.grayEF,
                modifier = Modifier.size(88.dp)
            ) {
                // Lesson image
            }
            HorizontalSpacer(width = 12.dp)
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "클래스 이름", style = KnowllyTheme.typography.subtitle2)
                    Spacer(modifier = Modifier.weight(1f))
                    if (lesson is PlayerLessonModel.Completed) {
                        ReviewOutlinedBadge(isReviewed = lesson.isReviewed)
                    }
                }
                VerticalSpacer(height = 2.dp)
                Text(text = "코치 이름", style = KnowllyTheme.typography.body1)
                VerticalSpacer(height = 6.dp)
                Text(
                    text = "2022년 6월 18일 (토)",
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray6B
                )
                Text(
                    text = "오후 6:00 (3시간 수업)",
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray6B
                )
            }
        }
        when (lesson) {
            is PlayerLessonModel.Matching -> {}
            is PlayerLessonModel.Scheduled -> {
                KakaoIdCopyButton(
                    kakaoId = lesson.coachKakaoId,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .height(40.dp)
                )
            }
            is PlayerLessonModel.Completed -> {
                if (!lesson.isReviewed) {
                    KnowllyContainedButton(
                        text = stringResource(id = R.string.player_review_button),
                        onClick = { /* TODO: 후기 페이지로 이동 */ },
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth()
                            .height(40.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PlayerContentHeader(tab: PlayerTabState.Tab) {
    val titleRes = when (tab) {
        PlayerTabState.Tab.Matching -> R.string.player_matching_title
        PlayerTabState.Tab.Scheduled -> R.string.player_scheduled_title
        PlayerTabState.Tab.Completed -> R.string.player_completed_title
    }
    val bannerRes = when (tab) {
        PlayerTabState.Tab.Matching -> R.string.player_matching_banner
        PlayerTabState.Tab.Scheduled -> R.string.player_scheduled_banner
        PlayerTabState.Tab.Completed -> R.string.player_completed_banner
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(id = titleRes), style = KnowllyTheme.typography.headline4)
        VerticalSpacer(height = 10.dp)
        Banner(text = stringResource(id = bannerRes))
    }
}

@Composable
fun PlayerContentEmpty(tab: PlayerTabState.Tab) {
    val bannerRes = when (tab) {
        PlayerTabState.Tab.Matching -> R.string.player_matching_empty
        PlayerTabState.Tab.Scheduled -> R.string.player_scheduled_empty
        PlayerTabState.Tab.Completed -> R.string.player_completed_empty
    }

    DashBanner(text = stringResource(id = bannerRes))
}