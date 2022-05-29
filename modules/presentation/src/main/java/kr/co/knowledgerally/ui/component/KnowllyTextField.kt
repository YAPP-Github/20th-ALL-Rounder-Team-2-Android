package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun KnowllyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeHolderText: String = "",
    helperTextEnabled: Boolean = false,
    helperText: String = "",
    counterEnabled: Boolean = false,
    counterMaxLength: Int = 0,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    minHeight: Dp = Dp.Unspecified,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val colors = KnowllyTextFieldDefaults.colors

    Column(modifier = modifier) {

        @OptIn(ExperimentalMaterial3Api::class)
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minHeight),
            enabled = enabled,
            textStyle = KnowllyTheme.typography.body1,
            cursorBrush = SolidColor(colors.cursorColor(isError).value),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            interactionSource = interactionSource,
            visualTransformation = visualTransformation,
            decorationBox = @Composable { innerTextField ->
                KnowllyTextFieldDefaults.DecorationBox(
                    value = value,
                    innerTextField = innerTextField,
                    enabled = enabled,
                    isError = isError,
                    singleLine = singleLine,
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    placeholder = placeHolderText,
                    colors = colors,
                )
            }
        )

        if (helperTextEnabled || counterEnabled) {
            VerticalSpacer(height = 2.dp)
            KnowllyTextFieldHelper(
                modifier = modifier.fillMaxWidth(),
                helperText = helperText,
                helperTextEnabled = helperTextEnabled,
                color = colors.labelColor(enabled, isError, interactionSource).value,
                counterLength = value.length,
                counterMaxLength = counterMaxLength,
                counterEnabled = counterEnabled,
            )
        }
    }
}

@Composable
private fun KnowllyTextFieldHelper(
    modifier: Modifier = Modifier,
    color: Color,
    helperTextEnabled: Boolean,
    helperText: String,
    counterEnabled: Boolean,
    counterLength: Int,
    counterMaxLength: Int,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        if (helperTextEnabled) {
            Text(
                text = helperText,
                modifier = Modifier.weight(1f),
                style = KnowllyTheme.typography.body2,
                color = color,
            )
        }
        if (helperTextEnabled && counterEnabled) {
            HorizontalSpacer(width = 10.dp)
        }
        if (counterEnabled) {
            Text(
                text = "${counterLength}/${counterMaxLength}",
                style = KnowllyTheme.typography.body2,
                color = color,
            )
        }
    }
}

@Immutable
object KnowllyTextFieldDefaults {
    private val BorderThickness = 1.dp
    private val ContentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp)
    private val TextFieldShape = RoundedCornerShape(8.dp)

    val colors
        @Composable
        get() = TextFieldDefaults.outlinedTextFieldColors(
            placeholderColor = KnowllyTheme.colors.grayCC,
            textColor = KnowllyTheme.colors.gray44,
            unfocusedBorderColor = KnowllyTheme.colors.grayDD,
            focusedBorderColor = KnowllyTheme.colors.positive,
            errorBorderColor = KnowllyTheme.colors.error,
            errorCursorColor = KnowllyTheme.colors.error,
            errorLabelColor = KnowllyTheme.colors.error,
            unfocusedLabelColor = KnowllyTheme.colors.gray8F,
            focusedLabelColor = KnowllyTheme.colors.gray8F,
            cursorColor = KnowllyTheme.colors.positive,
        )

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun DecorationBox(
        value: String,
        innerTextField: @Composable () -> Unit,
        enabled: Boolean,
        singleLine: Boolean,
        visualTransformation: VisualTransformation,
        interactionSource: InteractionSource,
        isError: Boolean,
        placeholder: String,
        colors: TextFieldColors,
    ) {
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = value,
            innerTextField = innerTextField,
            placeholder = {
                if (value.isEmpty()) {
                    Text(text = placeholder, style = KnowllyTheme.typography.body1)
                }
            },
            singleLine = singleLine,
            isError = isError,
            colors = colors,
            border = {
                TextFieldDefaults.BorderBox(
                    enabled = enabled,
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = colors,
                    shape = TextFieldShape,
                    focusedBorderThickness = BorderThickness,
                    unfocusedBorderThickness = BorderThickness,
                )
            },
            enabled = enabled,
            interactionSource = interactionSource,
            contentPadding = ContentPadding,
            visualTransformation = visualTransformation
        )
    }
}

@Preview("Default")
@Composable
private fun KnowllyTextFieldPreview() {
    KnowllyTheme {
        KnowllyTextField(
            value = "텍스트",
            onValueChange = { }
        )
    }
}

@Preview("Hint")
@Composable
private fun KnowllyTextFieldPreviewHint() {
    KnowllyTheme {
        KnowllyTextField(
            value = "",
            onValueChange = { },
            placeHolderText = "Hint"
        )
    }
}

@Preview("Error")
@Composable
private fun KnowllyTextFieldPreviewError() {
    KnowllyTheme {
        KnowllyTextField(
            value = "텍스트",
            onValueChange = { },
            isError = true
        )
    }
}

@Preview("Multiline")
@Composable
private fun KnowllyTextFieldPreviewMultiline() {
    KnowllyTheme {
        KnowllyTextField(
            value = "텍스트\n텍스트",
            onValueChange = { },
            singleLine = false
        )
    }
}

@Preview("HelperText")
@Composable
private fun KnowllyTextFieldPreviewHelperText() {
    KnowllyTheme {
        KnowllyTextField(
            value = "텍스트",
            onValueChange = { },
            helperText = "message",
            helperTextEnabled = true,
        )
    }
}

@Preview("Counter")
@Composable
private fun KnowllyTextFieldPreviewCounter() {
    KnowllyTheme {
        KnowllyTextField(
            value = "텍스트",
            onValueChange = { },
            counterMaxLength = 10,
            counterEnabled = true,
        )
    }
}
