package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kr.co.knowledgerally.ui.theme.KnowllyTheme

private const val MIN_TAG_LENGTH = 2
private val TagHeight = 48.dp

@Composable
fun TagTextField(
    tags: List<String>,
    maxCount: Int,
    placeholder: String,
    onAdd: (String) -> Unit,
    onRemove: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val items = remember(tags) { tags.distinct() }
    val focused by interactionSource.collectIsFocusedAsState()
    var text by remember { mutableStateOf("") }
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = modifier.defaultMinSize(minHeight = TagHeight),
        shape = RoundedCornerShape(8.dp),
        color = Color.Unspecified,
        border = BorderStroke(
            width = KnowllyDropdownDefaults.BorderThickness,
            color = if (focused) {
                KnowllyTheme.colors.positive
            } else {
                KnowllyTheme.colors.grayDD
            }
        )
    ) {
        BasicTextField(
            value = text,
            onValueChange = { value ->
                when {
                    tags.size >= maxCount -> Unit /* ignore */
                    value.endsWith(",") -> if (value.length > MIN_TAG_LENGTH) {
                        onAdd(value.dropLast(1))
                        text = ""
                    }
                    else -> {
                        text = value
                        coroutineScope.launch {
                            scrollState.animateScrollToItem(tags.lastIndex + 1)
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .onBackspace {
                    if (text.isBlank() && tags.isNotEmpty()) {
                        onRemove(tags.last())
                    }
                },
            textStyle = KnowllyTheme.typography.body2,
            singleLine = true,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(KnowllyTheme.colors.positive),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions {
                text = ""
                focusManager.clearFocus()
            },
            decorationBox = { innerTextField ->
                if (text.isEmpty() && tags.isEmpty()) {
                    TagPlaceholder(text = placeholder)
                }
                DecorationBox(
                    items = items,
                    scrollState = scrollState,
                    innerTextField = innerTextField
                )
            },
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
private fun Modifier.onBackspace(onBackspace: () -> Unit) = then(
    Modifier.onKeyEvent { event ->
        if (event.type == KeyEventType.KeyUp && event.key == Key.Backspace) {
            onBackspace()
            return@onKeyEvent true
        }
        false
    }
)

@Composable
private fun DecorationBox(
    items: List<String>,
    scrollState: LazyListState,
    innerTextField: @Composable () -> Unit,
) {
    LazyRow(
        state = scrollState,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(
            items = items,
            key = { it },
            itemContent = { Tag(it) }
        )
        item { innerTextField() }
    }
}

@Composable
private fun Tag(text: String) {
    Text(
        text = "#${text}",
        modifier = Modifier
            .background(
                KnowllyTheme.colors.grayF7,
                shape = RoundedCornerShape(62.dp)
            )
            .padding(horizontal = 8.dp, vertical = 3.dp),
        style = KnowllyTheme.typography.button2,
        color = KnowllyTheme.colors.gray44,
    )
}

@Composable
private fun TagPlaceholder(text: String) {
    Box(
        modifier = Modifier
            .padding(start = 16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = text,
            style = KnowllyTheme.typography.body1,
            color = KnowllyTheme.colors.grayCC
        )
    }
}
