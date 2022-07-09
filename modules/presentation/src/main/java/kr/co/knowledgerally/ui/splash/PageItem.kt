package kr.co.knowledgerally.ui.splash

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme

data class PageItem(
    val index: Int,
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    val trailingTitle: @Composable (() -> Unit)? = null,
    @StringRes
    val description: Int,
)

internal val PageItems = listOf(
    PageItem(
        index = 0,
        image = R.drawable.img_splash_1,
        title = R.string.splash_1_title,
        description = R.string.splash_1_description,
    ),
    PageItem(
        index = 1,
        image = R.drawable.img_splash_2,
        title = R.string.splash_2_title,
        description = R.string.splash_2_description,
    ),
    PageItem(
        index = 2,
        image = R.drawable.img_splash_3,
        title = R.string.splash_3_title,
        description = R.string.splash_3_description,
    ),
    PageItem(
        index = 3,
        image = R.drawable.img_splash_4,
        title = R.string.splash_4_title,
        trailingTitle = {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo_home),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 4.dp)
                    .size(84.dp, 24.dp),
                tint = KnowllyTheme.colors.gray00,
            )
        },
        description = R.string.splash_4_description,
    ),
)
