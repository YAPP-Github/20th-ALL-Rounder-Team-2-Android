package kr.co.knowledgerally.ui.player

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kr.co.knowledgerally.domain.model.LectureInfo
import kr.co.knowledgerally.domain.model.Schedule
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
    uiState: PlayerUiState,
    navigateToLecture: (lectureInfoId: Long) -> Unit,
    navigateToReview: (lectureId: Long) -> Unit
) {
    when (tab) {
        PlayerTabState.Tab.Matching -> Matching(
            uiState.matchingLectures,
            navigateToLecture = navigateToLecture
        )
        PlayerTabState.Tab.Scheduled -> Scheduled(
            uiState.scheduledLectures,
            navigateToLecture = navigateToLecture
        )
        PlayerTabState.Tab.Completed -> Completed(
            uiState.completedLectures,
            navigateToLecture = navigateToLecture,
            navigateToReview = navigateToReview
        )
    }
}

@Composable
private fun Matching(
    items: List<LectureItemUiState.Matching>,
    navigateToLecture: (lectureInfoId: Long) -> Unit
) {
    if (items.isEmpty()) {
        PlayerContentEmpty(PlayerTabState.Tab.Matching)
    } else {
        LazyColumn {
            itemsIndexed(
                items = items,
                key = { _, item -> item.lecture.id }
            ) { index, item ->
                MatchingItem(item = item, navigateToLecture = navigateToLecture)

                if (index == items.lastIndex) {
                    KnowllyDivider()
                }
            }
        }
    }
}

@Composable
private fun Scheduled(
    items: List<LectureItemUiState.Scheduled>,
    navigateToLecture: (lectureInfoId: Long) -> Unit
) {
    if (items.isEmpty()) {
        PlayerContentEmpty(PlayerTabState.Tab.Scheduled)
    } else {
        LazyColumn {
            itemsIndexed(
                items = items,
                key = { _, item -> item.lecture.id }
            ) { index, item ->
                ScheduleItem(item = item, navigateToLecture = navigateToLecture)

                if (index == items.lastIndex) {
                    KnowllyDivider()
                }
            }
        }
    }
}

@Composable
private fun Completed(
    items: List<LectureItemUiState.Completed>,
    navigateToLecture: (lectureInfoId: Long) -> Unit,
    navigateToReview: (lectureId: Long) -> Unit
) {
    if (items.isEmpty()) {
        PlayerContentEmpty(PlayerTabState.Tab.Completed)
    } else {
        LazyColumn {
            itemsIndexed(
                items = items,
                key = { _, item -> item.lecture.id }
            ) { index, item ->
                CompletedItem(
                    item = item,
                    navigateToLecture = navigateToLecture,
                    navigateToReview = navigateToReview
                )

                if (index == items.lastIndex) {
                    KnowllyDivider()
                }
            }
        }
    }
}

@Composable
private fun MatchingItem(
    item: LectureItemUiState.Matching,
    navigateToLecture: (lectureInfoId: Long) -> Unit
) {
    LectureItem(
        lectureInfo = item.lectureInfo,
        schedule = item.lecture.schedule,
        navigateToLecture = navigateToLecture
    )
}

@Composable
private fun ScheduleItem(
    item: LectureItemUiState.Scheduled,
    navigateToLecture: (lectureInfoId: Long) -> Unit
) {
    LectureItem(
        lectureInfo = item.lectureInfo,
        schedule = item.lecture.schedule,
        bottom = {
            KakaoIdCopyButton(
                kakaoId = item.lectureInfo.coach.profile.kakaoId,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            )
        },
        navigateToLecture = navigateToLecture
    )
}

@Composable
private fun CompletedItem(
    item: LectureItemUiState.Completed,
    navigateToLecture: (lectureInfoId: Long) -> Unit,
    navigateToReview: (lectureId: Long) -> Unit
) {
    val isReviewed = item.lecture.isReviewed
    LectureItem(
        lectureInfo = item.lectureInfo,
        schedule = item.lecture.schedule,
        trailingTitle = { ReviewOutlinedBadge(isReviewed = isReviewed) },
        bottom = {
            if (!isReviewed) {
                KnowllyContainedButton(
                    text = stringResource(id = R.string.player_review_button),
                    onClick = { navigateToReview(item.lecture.id) },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .height(40.dp)
                )
            }
        },
        navigateToLecture = navigateToLecture
    )
}

@Composable
private fun LectureItem(
    lectureInfo: LectureInfo,
    schedule: Schedule,
    trailingTitle: @Composable () -> Unit = { },
    bottom: @Composable () -> Unit = { },
    navigateToLecture: (lectureInfoId: Long) -> Unit
) {
    Column(modifier = Modifier
        .clickable { navigateToLecture(lectureInfo.id) }
        .fillMaxWidth()
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
                val imageUrl = lectureInfo.thumbnailImageUrl
                if (imageUrl != null) {
                    AsyncImage(
                        model = imageUrl,
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
                        text = lectureInfo.topic,
                        style = KnowllyTheme.typography.subtitle2
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    trailingTitle()
                }
                VerticalSpacer(height = 2.dp)
                Text(
                    text = lectureInfo.coach.profile.username,
                    style = KnowllyTheme.typography.body1
                )
                VerticalSpacer(height = 6.dp)
                Text(
                    text = schedule.startAt.format(
                        DateTimeFormatter.ofPattern(stringResource(id = R.string.lecture_date_format))
                    ),
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray6B
                )
                Text(
                    text = "${
                        schedule.startAt.format(
                            DateTimeFormatter.ofPattern(
                                stringResource(id = R.string.lecture_time_format)
                            )
                        )
                    } ${
                        stringResource(
                            R.string.lecture_runningtime_format,
                            schedule.runningTime
                        )
                    }",
                    style = KnowllyTheme.typography.body2,
                    color = KnowllyTheme.colors.gray6B
                )
            }
        }
        bottom()
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
