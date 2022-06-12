package kr.co.knowledgerally.ui.coach

import androidx.annotation.StringRes
import kr.co.knowledgerally.ui.R

enum class CoachTab(
    val index: Int,
    @StringRes val tabTextResId: Int,
    @StringRes val titleTextResId: Int,
    @StringRes val bannerTextResId: Int,
) {
    Matching(
        index = 0,
        tabTextResId = R.string.matching,
        titleTextResId = R.string.matching_class,
        bannerTextResId = R.string.coach_matching_banner_text,
    ),
    Scheduled(
        index = 1,
        tabTextResId = R.string.scheduled,
        titleTextResId = R.string.scheduled_class,
        bannerTextResId = R.string.coach_scheduled_banner_text,
    ),
    Completed(
        index = 2,
        tabTextResId = R.string.completed,
        titleTextResId = R.string.completed_class,
        bannerTextResId = R.string.coach_completed_banner_text,
    );
}
