package kr.co.knowledgerally.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyTextField
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val nameState by viewModel.name.collectAsState()
    val introductionState by viewModel.introduction.collectAsState()
    val canUpload by viewModel.canUpload.collectAsState()

    ProfileScreen(
        nameState = nameState,
        onNameChange = viewModel::updateName,
        introductionState = introductionState,
        onIntroductionChange = viewModel::updateIntroduction,
        canUpload = canUpload,
        onUpload = viewModel::uploadProfile,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun ProfileScreen(
    nameState: TextUiState,
    onNameChange: (String) -> Unit,
    introductionState: TextUiState,
    onIntroductionChange: (String) -> Unit,
    canUpload: Boolean,
    onUpload: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(horizontal = 24.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 64.dp)
        ) {
            ProfileTitle(text = stringResource(id = R.string.profile_title))

            ProfileImage(
                modifier = Modifier
                    .padding(top = 36.dp, bottom = 16.dp)
                    .size(156.dp)
                    .align(Alignment.CenterHorizontally)
            )

            ProfileSubtitle(text = stringResource(id = R.string.profile_name))
            ProfileTextField(
                value = nameState.text,
                onValueChange = onNameChange,
                maxLength = nameState.maxLength,
                placeholder = stringResource(id = R.string.profile_name_hint),
                singleLine = true,
                helperText = "message",
            )

            VerticalSpacer(height = 20.dp)

            ProfileSubtitle(text = stringResource(id = R.string.profile_introduction))
            ProfileTextField(
                value = introductionState.text,
                onValueChange = onIntroductionChange,
                maxLength = introductionState.maxLength,
                placeholder = stringResource(id = R.string.profile_introduction_hint),
                singleLine = false,
                minHeight = 180.dp,
            )

            // Button Size + Button Padding
            VerticalSpacer(height = 90.dp)
        }

        ProfileUploadButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = onUpload,
            enabled = canUpload
        )
    }
}

@Composable
private fun ProfileTitle(text: String) {
    Text(text = text, style = KnowllyTheme.typography.headline4)
}

@Composable
private fun ProfileSubtitle(text: String) {
    Text(text = text, style = KnowllyTheme.typography.subtitle1)
}

@Composable
private fun ProfileImage(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .clickable { /* TODO */ },
            color = KnowllyTheme.colors.grayCC,
        ) {
            // TODO: Image
        }
        Surface(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(36.dp),
            border = BorderStroke(1.dp, KnowllyTheme.colors.grayFF),
            color = KnowllyTheme.colors.gray8F,
            shape = CircleShape
        ) {
            // TODO: Icon
        }
    }
}

@Composable
private fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    placeholder: String,
    singleLine: Boolean,
    modifier: Modifier = Modifier,
    helperText: String = "",
    minHeight: Dp = Dp.Unspecified,
) {
    KnowllyTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.padding(top = 12.dp),
        placeholder = placeholder,
        singleLine = singleLine,
        helperText = helperText,
        helperTextEnabled = helperText.isNotBlank(),
        counterMaxLength = maxLength,
        counterEnabled = true,
        minHeight = minHeight
    )
}

@Composable
private fun ProfileUploadButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    KnowllyContainedButton(
        text = stringResource(id = R.string.profile_upload),
        onClick = onClick,
        modifier = modifier
            .padding(bottom = 24.dp, top = 20.dp)
            .fillMaxWidth(),
        enabled = enabled
    )
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    KnowllyTheme {
        ProfileScreen(
            nameState = TextUiState.default(10),
            onNameChange = { },
            introductionState = TextUiState.default(100),
            onIntroductionChange = { },
            canUpload = true,
            onUpload = { },
        )
    }
}
