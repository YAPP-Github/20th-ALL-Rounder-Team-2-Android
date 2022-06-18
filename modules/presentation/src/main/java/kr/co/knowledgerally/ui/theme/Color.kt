package kr.co.knowledgerally.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

val Orange = Color(0xFFFF9534)
val OrangeDark = Color(0xFFFF7A00)
val OrangeLight = Color(0xFFFFF4EB)
val Lime = Color(0xFFC7E02A)
val LimeDark = Color(0xFFA5BB1B)
val LimeLight = Color(0xFFF4F9D4)
val Indigo = Color(0xFF172351)
val IndigoLight = Color(0xFFF0F4FF)
val GrayFF = Color(0xFFFFFFFF)
val GrayF7 = Color(0xFFF7F7F7)
val GrayEF = Color(0xFFEFEFEF)
val GrayDD = Color(0xFFDDDDDD)
val GrayCC = Color(0xFFCCCCCC)
val Gray8F = Color(0xFF8F8F8F)
val Gray6B = Color(0xFF6B6B6B)
val Gray44 = Color(0xFF444444)
val Gray00 = Color(0xFF000000)
val Red = Color(0xFFE72749)
val Blue = Color(0xFF779DFF)

val MaterialColors = androidx.compose.material3.lightColorScheme(
    primary = Orange,
    secondary = Lime,
    error = Red,
    background = GrayFF,
    surface = GrayFF,
)

@Stable
class KnowllyColors(
    primary: Color,
    primaryDark: Color,
    primaryLight: Color,
    secondary: Color,
    secondaryLimeDark: Color,
    secondaryLimeLight: Color,
    secondaryIndigo: Color,
    secondaryIndigoLight: Color,
    grayFF: Color,
    grayF7: Color,
    grayEF: Color,
    grayDD: Color,
    grayCC: Color,
    gray8F: Color,
    gray6B: Color,
    gray44: Color,
    gray00: Color,
    error: Color,
    positive: Color,
) {
    var primary: Color by mutableStateOf(primary, structuralEqualityPolicy())
        private set
    var primaryDark: Color by mutableStateOf(primaryDark, structuralEqualityPolicy())
        private set
    var primaryLight: Color by mutableStateOf(primaryLight, structuralEqualityPolicy())
        private set
    var secondary: Color by mutableStateOf(secondary, structuralEqualityPolicy())
        private set
    var secondaryLimeDark: Color by mutableStateOf(secondaryLimeDark, structuralEqualityPolicy())
        private set
    var secondaryLimeLight: Color by mutableStateOf(secondaryLimeLight, structuralEqualityPolicy())
        private set
    var secondaryIndigo: Color by mutableStateOf(secondaryIndigo, structuralEqualityPolicy())
        private set
    var secondaryIndigoLight: Color by mutableStateOf(
        secondaryIndigoLight,
        structuralEqualityPolicy()
    )
        private set
    var grayFF: Color by mutableStateOf(grayFF, structuralEqualityPolicy())
        private set
    var grayF7: Color by mutableStateOf(grayF7, structuralEqualityPolicy())
        private set
    var grayEF: Color by mutableStateOf(grayEF, structuralEqualityPolicy())
        private set
    var grayDD: Color by mutableStateOf(grayDD, structuralEqualityPolicy())
        private set
    var grayCC: Color by mutableStateOf(grayCC, structuralEqualityPolicy())
        private set
    var gray8F: Color by mutableStateOf(gray8F, structuralEqualityPolicy())
        private set
    var gray6B: Color by mutableStateOf(gray6B, structuralEqualityPolicy())
        private set
    var gray44: Color by mutableStateOf(gray44, structuralEqualityPolicy())
        private set
    var gray00: Color by mutableStateOf(gray00, structuralEqualityPolicy())
        private set
    var error: Color by mutableStateOf(error, structuralEqualityPolicy())
        private set
    var positive: Color by mutableStateOf(positive, structuralEqualityPolicy())
        private set

    fun copy(
        primary: Color = this.primary,
        primaryDark: Color = this.primaryDark,
        primaryLight: Color = this.primaryLight,
        secondary: Color = this.secondary,
        secondaryLimeDark: Color = this.secondaryLimeDark,
        secondaryLimeLight: Color = this.secondaryLimeLight,
        secondaryIndigo: Color = this.secondaryIndigo,
        secondaryIndigoLight: Color = this.secondaryIndigoLight,
        grayFF: Color = this.grayFF,
        grayF7: Color = this.grayF7,
        grayEF: Color = this.grayEF,
        grayDD: Color = this.grayDD,
        grayCC: Color = this.grayCC,
        gray8F: Color = this.gray8F,
        gray6B: Color = this.gray6B,
        gray44: Color = this.gray44,
        gray00: Color = this.gray00,
        error: Color = this.error,
        positive: Color = this.positive,
    ) = KnowllyColors(
        primary = primary,
        primaryDark = primaryDark,
        primaryLight = primaryLight,
        secondary = secondary,
        secondaryLimeDark = secondaryLimeDark,
        secondaryLimeLight = secondaryLimeLight,
        secondaryIndigo = secondaryIndigo,
        secondaryIndigoLight = secondaryIndigoLight,
        grayFF = grayFF,
        grayF7 = grayF7,
        grayEF = grayEF,
        grayDD = grayDD,
        grayCC = grayCC,
        gray8F = gray8F,
        gray6B = gray6B,
        gray44 = gray44,
        gray00 = gray00,
        error = error,
        positive = positive,
    )
}

fun lightColors(
    primary: Color = Orange,
    primaryDark: Color = OrangeDark,
    primaryLight: Color = OrangeLight,
    secondary: Color = Lime,
    secondaryLimeDark: Color = LimeDark,
    secondaryLimeLight: Color = LimeLight,
    secondaryIndigo: Color = Indigo,
    secondaryIndigoLight: Color = IndigoLight,
    grayFF: Color = GrayFF,
    grayF7: Color = GrayF7,
    grayEF: Color = GrayEF,
    grayDD: Color = GrayDD,
    grayCC: Color = GrayCC,
    gray8F: Color = Gray8F,
    gray6B: Color = Gray6B,
    gray44: Color = Gray44,
    gray00: Color = Gray00,
    error: Color = Red,
    positive: Color = Blue,
) = KnowllyColors(
    primary = primary,
    primaryDark = primaryDark,
    primaryLight = primaryLight,
    secondary = secondary,
    secondaryLimeDark = secondaryLimeDark,
    secondaryLimeLight = secondaryLimeLight,
    secondaryIndigo = secondaryIndigo,
    secondaryIndigoLight = secondaryIndigoLight,
    grayFF = grayFF,
    grayF7 = grayF7,
    grayEF = grayEF,
    grayDD = grayDD,
    grayCC = grayCC,
    gray8F = gray8F,
    gray6B = gray6B,
    gray44 = gray44,
    gray00 = gray00,
    error = error,
    positive = positive,
)

internal val LocalColors = staticCompositionLocalOf { lightColors() }
