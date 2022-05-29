package kr.co.knowledgerally.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

@Composable
fun KnowllyTheme(
    colors: KnowllyColors = KnowllyTheme.colors,
    typography: KnowllyTypography = KnowllyTheme.typography,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember {
        // Explicitly creating a new object here so we don't mutate the initial [colors]
        // provided, and overwrite the values set in it.
        colors.copy()
    }

    @OptIn(ExperimentalFoundationApi::class)
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides typography,
        LocalContentColor provides colors.gray00,
        LocalOverScrollConfiguration provides null
    ) {
        MaterialTheme(
            colors = MaterialColors,
            shapes = MaterialShapes,
            content = content
        )
    }
}

object KnowllyTheme {

    val colors: KnowllyColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: KnowllyTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}
