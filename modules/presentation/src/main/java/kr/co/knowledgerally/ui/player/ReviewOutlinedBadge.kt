package kr.co.knowledgerally.ui.player

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.OutlinedBadge
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ReviewOutlinedBadge(
    isReviewed: Boolean
) {
    val textRes = if (isReviewed) {
        R.string.player_reviewed
    } else {
        R.string.player_not_reviewed
    }
    val badgeColor = if (isReviewed) {
        KnowllyTheme.colors.primaryDark
    } else {
        KnowllyTheme.colors.gray8F
    }

    OutlinedBadge(
        text = stringResource(id = textRes),
        color = badgeColor,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
        icon = {
            if (isReviewed) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 2.dp),
                    tint = KnowllyTheme.colors.primaryDark,
                )
            }
        })
}