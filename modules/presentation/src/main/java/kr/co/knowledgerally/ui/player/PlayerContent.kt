package kr.co.knowledgerally.ui.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.Banner
import kr.co.knowledgerally.ui.component.DashBanner
import kr.co.knowledgerally.ui.component.HorizontalSpacer
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyDivider
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme
import java.time.format.DateTimeFormatter

@Composable
fun PlayerContent(
    tab: PlayerTabState.Tab,
    uiState: PlayerUiState
) {
    val lectures = when (tab) {
        PlayerTabState.Tab.Matching -> uiState.matchingLectures
        PlayerTabState.Tab.Scheduled -> uiState.scheduledLectures
        PlayerTabState.Tab.Completed -> uiState.completedLectures
    }

    if (lectures.isNotEmpty()) {
        Column {
            PlayerContentList(items = lectures)
            KnowllyDivider()
        }
    } else {
        PlayerContentEmpty(tab = tab)
    }
}

@Composable
fun PlayerContentList(items: List<LectureItemUiState>) {
    LazyColumn {
        items(lectures) { lecture ->
            PlayerContentListItem(item = lecture)
        }
    }
}

@Composable
fun PlayerContentListItem(item: LectureItemUiState) {
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
                if (item.lecture.imageUrls.isNotEmpty()) {
                    AsyncImage(
                        model = item.lecture.imageUrls[0],
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            HorizontalSpacer(width = 12.dp)
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.lecture.title,
                        style = KnowllyTheme.typography.subtitle2
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (item is PlayerLectureUiState.Completed) {
                        ReviewOutlinedBadge(isReviewed = item.isReviewed)
                    }
                }
                VerticalSpacer(height = 2.dp)
                Text(
                    text = item.coach.profile.username,
                    style = KnowllyTheme.typography.body1
                )
                VerticalSpacer(height = 6.dp)
                Text(
                    text = item.lecture.startAt.format(
                        DateTimeFormatter.ofPattern(stringResource(id = R.string.lecture_date_format))
                    ),
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray6B
                )
                Text(
                    text = "${
                        item.lecture.startAt.format(
                            DateTimeFormatter.ofPattern(
                                stringResource(id = R.string.lecture_time_format)
                            )
                        )
                    } ${
                        stringResource(
                            R.string.lecture_runningtime_format,
                            item.lecture.runningTime
                        )
                    }",
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray6B
                )
            }
        }
        when (item) {
            is PlayerLectureUiState.Matching -> {}
            is PlayerLectureUiState.Scheduled -> {
                KakaoIdCopyButton(
                    kakaoId = item.coach.profile.kakaoId,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .height(40.dp)
                )
            }
            is PlayerLectureUiState.Completed -> {
                if (!item.isReviewed) {
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
