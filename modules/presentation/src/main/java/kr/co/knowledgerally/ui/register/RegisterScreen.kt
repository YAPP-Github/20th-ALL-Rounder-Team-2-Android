package kr.co.knowledgerally.ui.register

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.AddPhotoIcon
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyDropdown
import kr.co.knowledgerally.ui.component.KnowllyMultilineTextField
import kr.co.knowledgerally.ui.component.KnowllySinglelineTextField
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.NavigationType
import kr.co.knowledgerally.ui.component.PageIndicator
import kr.co.knowledgerally.ui.component.TagTextField
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    navigateUp: () -> Unit,
    navigateToSchedule: () -> Unit,
) {
    val state = rememberRegisterState()
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris -> state.updateImageUris(uris) }
    )
    ModalBottomSheetLayout(
        sheetContent = {
            CategorySelectSheetContent(
                sheetState = sheetState,
                onSelect = { state.category = it },
            )
        },
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetElevation = Dp.Unspecified,
        sheetBackgroundColor = KnowllyTheme.colors.grayFF,
        sheetShape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
    ) {
        RegisterContent(
            state = state,
            navigateUp = navigateUp,
            onAskCategory = { coroutineScope.launch { sheetState.show() } },
            onPickImage = { launcher.launch("image/*") },
            navigateToSchedule = navigateToSchedule
        )
    }
}

@Composable
fun RegisterContent(
    state: RegisterState,
    navigateUp: () -> Unit,
    onAskCategory: () -> Unit,
    onPickImage: () -> Unit,
    navigateToSchedule: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        KnowllyTopAppBar(NavigationType.Close, onNavigationClick = navigateUp)
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                ClassIndicator()
                Text(
                    text = stringResource(id = R.string.register_title),
                    modifier = Modifier.padding(top = 24.dp),
                    style = KnowllyTheme.typography.headline3,
                )
                ClassCategory(
                    category = state.category,
                    onClick = onAskCategory,
                    modifier = Modifier.padding(top = 40.dp),
                )
                ClassName(
                    value = state.name,
                    onValueChange = { state.name = it },
                    modifier = Modifier.padding(top = 24.dp),
                )
                ClassIntroduce(
                    value = state.introduce,
                    onValueChange = { state.introduce = it },
                    modifier = Modifier.padding(top = 24.dp),
                )
                ClassTags(
                    values = state.tags.toList(),
                    onAdd = state::addTag,
                    onRemove = state::removeTag,
                    modifier = Modifier.padding(top = 24.dp)
                )
            }
            ClassImages(
                uris = state.imageUris,
                onPick = onPickImage,
                onRemove = { state.removeImageUri(it) },
                modifier = Modifier.padding(top = 24.dp),
            )
            KnowllyContainedButton(
                text = stringResource(id = R.string.next),
                onClick = navigateToSchedule,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 24.dp)
                    .fillMaxWidth(),
                enabled = state.canNext,
            )
        }
    }
}

@Composable
private fun ClassIndicator(modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(top = 12.dp)) {
        PageIndicator(value = 1, isActive = true)
        PageIndicator(
            value = 2,
            isActive = false,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun ClassCategory(
    category: CategoryItem?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.register_category),
            style = KnowllyTheme.typography.subtitle2,
            color = KnowllyTheme.colors.gray44,
        )
        KnowllyDropdown(
            value = if (category == null) {
                stringResource(id = R.string.register_category_placeholder)
            } else {
                stringResource(id = category.textResId)
            },
            onClick = onClick,
            modifier = Modifier.padding(top = 8.dp),
            isSelected = category != null,
        )
    }
}

@Composable
private fun ClassName(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.register_name),
            style = KnowllyTheme.typography.subtitle2,
            color = KnowllyTheme.colors.gray44,
        )
        KnowllySinglelineTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.padding(top = 8.dp),
            counterEnabled = true,
            counterMaxLength = 20,
            placeholder = stringResource(id = R.string.register_name_placeholder),
        )
    }
}

@Composable
private fun ClassIntroduce(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.register_introduce),
            style = KnowllyTheme.typography.subtitle2,
            color = KnowllyTheme.colors.gray44,
        )
        Text(
            text = stringResource(id = R.string.register_introduce_description),
            modifier = Modifier.padding(top = 2.dp),
            style = KnowllyTheme.typography.body2,
            color = KnowllyTheme.colors.gray8F,
        )
        KnowllyMultilineTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.padding(top = 8.dp),
            counterEnabled = true,
            counterMaxLength = 600,
            placeholder = stringResource(id = R.string.register_introduce_placeholder),
            minHeight = 240.dp,
        )
    }
}

@Composable
private fun ClassTags(
    values: List<String>,
    onAdd: (String) -> Unit,
    onRemove: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.register_tag),
                style = KnowllyTheme.typography.subtitle2,
                color = KnowllyTheme.colors.gray44,
            )
            Text(
                text = stringResource(id = R.string.register_tag_max_count),
                modifier = Modifier.padding(start = 4.dp),
                style = KnowllyTheme.typography.body2,
                color = KnowllyTheme.colors.gray44,
            )
        }
        TagTextField(
            tags = values,
            maxCount = 5,
            placeholder = stringResource(id = R.string.register_tag_placeholder),
            onAdd = onAdd,
            onRemove = onRemove,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.register_tag_helper_text),
            modifier = Modifier.padding(start = 4.dp, top = 2.dp),
            style = KnowllyTheme.typography.body2,
            color = KnowllyTheme.colors.gray8F,
        )
    }
}

@Composable
private fun ClassImages(
    uris: List<Uri>,
    onPick: () -> Unit,
    onRemove: (Uri) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.register_image),
                style = KnowllyTheme.typography.subtitle2,
                color = KnowllyTheme.colors.gray44,
            )
            Text(
                text = stringResource(id = R.string.register_image_optional),
                modifier = Modifier.padding(start = 4.dp),
                style = KnowllyTheme.typography.body2,
                color = KnowllyTheme.colors.gray44,
            )
        }
        Text(
            text = stringResource(id = R.string.register_image_description),
            modifier = Modifier.padding(start = 24.dp, top = 2.dp, end = 24.dp),
            style = KnowllyTheme.typography.body2,
            color = KnowllyTheme.colors.gray8F,
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 24.dp),
        ) {
            addImage(size = uris.size, onClick = onPick)
            images(
                uris = uris,
                onRemove = onRemove
            )
        }
    }
}

private fun LazyListScope.addImage(
    size: Int,
    onClick: () -> Unit
) {
    item {
        ImageButton(
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onClick() },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "${size}/5",
                        style = KnowllyTheme.typography.subtitle2,
                        color = KnowllyTheme.colors.gray8F,
                    )
                }
            },
            icon = {
                AddPhotoIcon(
                    size = 16.dp,
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.BottomEnd)
                )
            },
        )
    }
}

private fun LazyListScope.images(
    uris: List<Uri>,
    onRemove: (Uri) -> Unit,
) {
    items(
        items = uris,
        key = { it.toString() }
    ) { uri ->
        ImageButton(
            content = {
                AsyncImage(
                    model = uri,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            },
            icon = {
                Surface(
                    shape = CircleShape,
                    modifier = Modifier
                        .size(26.dp)
                        .align(Alignment.TopEnd),
                    color = KnowllyTheme.colors.grayEF,
                    onClick = { onRemove(uri) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clear),
                        contentDescription = null,
                        modifier = Modifier.requiredSize(16.dp)
                    )
                }
            },
        )
    }
}

@Composable
private fun ImageButton(
    content: @Composable BoxScope.() -> Unit,
    icon: @Composable BoxScope.() -> Unit,
) {
    Box(modifier = Modifier.size(width = 102.dp, height = 108.dp)) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(KnowllyTheme.colors.grayEF)
                .align(Alignment.CenterStart),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
        icon()
    }
}

@Preview(widthDp = 360, heightDp = 1500, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    KnowllyTheme {
        RegisterContent(
            state = rememberRegisterState(),
            navigateUp = { },
            onAskCategory = { },
            onPickImage = { },
            navigateToSchedule = { },
        )
    }
}
