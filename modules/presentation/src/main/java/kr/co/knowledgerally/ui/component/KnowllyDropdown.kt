package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun KnowllyDropdown(
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester: FocusRequester = remember { FocusRequester() }
    val colors: DropdownColors = KnowllyDropdownDefaults.colors()
    val borderColor by colors.borderColor(isSelected, interactionSource)
    val textColor by colors.textColor(isSelected, interactionSource)

    Surface(
        modifier = modifier
            .defaultMinSize(minHeight = KnowllyDropdownDefaults.MinHeight)
            .dropdownFocusModifier(
                focusRequester = focusRequester,
                interactionSource = interactionSource
            ),
        shape = RoundedCornerShape(8.dp),
        color = Color.Unspecified,
        border = BorderStroke(
            width = KnowllyDropdownDefaults.BorderThickness,
            color = borderColor
        )
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    focusRequester.requestFocus()
                    onClick()
                }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value,
                modifier = Modifier.weight(1f),
                style = KnowllyTheme.typography.body1,
                color = textColor,
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_expand_more),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(24.dp),
                tint = Color.Unspecified,
            )
        }

    }
}

private fun Modifier.dropdownFocusModifier(
    focusRequester: FocusRequester,
    interactionSource: MutableInteractionSource,
) = this
    .focusRequester(focusRequester)
    .focusable(interactionSource = interactionSource)

object KnowllyDropdownDefaults {
    val MinHeight: Dp = 48.dp
    val BorderThickness: Dp = 1.dp

    @Composable
    fun colors(
        defaultTextColor: Color = KnowllyTheme.colors.gray8F,
        selectedTextColor: Color = KnowllyTheme.colors.gray44,
        focusedTextColor: Color = KnowllyTheme.colors.gray44,
        defaultBorderColor: Color = KnowllyTheme.colors.grayDD,
        selectedBorderColor: Color = KnowllyTheme.colors.grayDD,
        focusedBorderColor: Color = KnowllyTheme.colors.positive,
    ) = DefaultDropdownColors(
        defaultTextColor = defaultTextColor,
        selectedTextColor = selectedTextColor,
        focusedTextColor = focusedTextColor,
        defaultBorderColor = defaultBorderColor,
        selectedBorderColor = selectedBorderColor,
        focusedBorderColor = focusedBorderColor,
    )
}

@Stable
interface DropdownColors {

    @Composable
    fun textColor(
        isSelected: Boolean,
        interactionSource: InteractionSource
    ): State<Color>

    @Composable
    fun borderColor(
        isSelected: Boolean,
        interactionSource: InteractionSource
    ): State<Color>
}

@Immutable
class DefaultDropdownColors(
    private val defaultTextColor: Color,
    private val selectedTextColor: Color,
    private val focusedTextColor: Color,
    private val defaultBorderColor: Color,
    private val selectedBorderColor: Color,
    private val focusedBorderColor: Color,
) : DropdownColors {

    @Composable
    override fun textColor(
        isSelected: Boolean,
        interactionSource: InteractionSource
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                focused -> focusedTextColor
                isSelected -> selectedTextColor
                else -> defaultTextColor
            }
        )
    }

    @Composable
    override fun borderColor(
        isSelected: Boolean,
        interactionSource: InteractionSource
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        return rememberUpdatedState(
            when {
                focused -> focusedBorderColor
                isSelected -> selectedBorderColor
                else -> defaultBorderColor
            }
        )
    }
}
