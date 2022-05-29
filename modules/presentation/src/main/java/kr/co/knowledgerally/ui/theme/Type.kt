package kr.co.knowledgerally.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kr.co.knowledgerally.ui.R

private val Suit = FontFamily(
    Font(R.font.suit_bold, FontWeight.Bold),
    Font(R.font.suit_semi_bold, FontWeight.SemiBold),
    Font(R.font.suit_medium, FontWeight.Medium),
    Font(R.font.suit_regular, FontWeight.Normal)
)

private val Typography = KnowllyTypography(
    headline1 = TextStyle(
        fontFamily = Suit,
        fontSize = 34.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 44.sp,
    ),
    headline2 = TextStyle(
        fontFamily = Suit,
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 38.sp,
    ),
    headline3 = TextStyle(
        fontFamily = Suit,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 34.sp,
    ),
    headline4 = TextStyle(
        fontFamily = Suit,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 30.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = Suit,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp,
        letterSpacing = (-0.25).sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Suit,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp,
    ),
    subtitle3 = TextStyle(
        fontFamily = Suit,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.sp,
    ),
    subtitle4 = TextStyle(
        fontFamily = Suit,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        letterSpacing = (-0.25).sp
    ),
    body1 = TextStyle(
        fontFamily = Suit,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 19.sp,
        letterSpacing = (-0.25).sp
    ),
    body2 = TextStyle(
        fontFamily = Suit,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp,
        letterSpacing = (-0.25).sp
    ),
    button1 = TextStyle(
        fontFamily = Suit,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
    ),
    button2 = TextStyle(
        fontFamily = Suit,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
    ),
    caption = TextStyle(
        fontFamily = Suit,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
    ),
    overline = TextStyle(
        fontFamily = Suit,
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        letterSpacing = (0.2).sp
    ),
)

@Immutable
data class KnowllyTypography(
    val headline1: TextStyle,
    val headline2: TextStyle,
    val headline3: TextStyle,
    val headline4: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val subtitle3: TextStyle,
    val subtitle4: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val button1: TextStyle,
    val button2: TextStyle,
    val caption: TextStyle,
    val overline: TextStyle,
)

internal val LocalTypography = staticCompositionLocalOf { Typography }
