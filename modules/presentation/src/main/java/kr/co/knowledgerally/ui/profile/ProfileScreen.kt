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
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyTextField
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val name by viewModel.name.collectAsState()
    val introduction by viewModel.introduction.collectAsState()
    val uploadEnabled by viewModel.canUpload.collectAsState()

    ProfileScreen(
        nameState = name,
        onNameChange = viewModel::updateName,
        introductionState = introduction,
        onIntroductionChange = viewModel::updateIntroduction,
        uploadEnabled = uploadEnabled,
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
    uploadEnabled: Boolean,
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

            ProfileTitle(text = stringResource(id = R.string.profile_name))
            VerticalSpacer(height = 12.dp)
            KnowllyTextField(
                value = nameState.text,
                onValueChange = onNameChange,
                placeHolderText = stringResource(id = R.string.profile_name_hint),
                singleLine = true,
                helperText = "message",
                helperTextEnabled = true,
                counterMaxLength = nameState.maxLength,
                counterEnabled = true,
            )

            VerticalSpacer(height = 20.dp)

            ProfileTitle(text = stringResource(id = R.string.profile_introduction))
            VerticalSpacer(height = 12.dp)
            KnowllyTextField(
                value = introductionState.text,
                onValueChange = onIntroductionChange,
                placeHolderText = stringResource(id = R.string.profile_introduction_hint),
                helperText = "message",
                singleLine = false,
                counterMaxLength = introductionState.maxLength,
                counterEnabled = true,
                minHeight = 180.dp,
            )

            // Button Size + Button Padding
            VerticalSpacer(height = 90.dp)
        }

        KnowllyContainedButton(
            text = stringResource(id = R.string.profile_upload),
            onClick = onUpload,
            modifier = Modifier
                .padding(bottom = 24.dp, top = 20.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            enabled = uploadEnabled
        )
    }
}

@Composable
private fun ProfileTitle(text: String) {
    Text(text = text, style = KnowllyTheme.typography.headline4)
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

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    KnowllyTheme {
        ProfileScreen(
            nameState = TextUiState.default(10),
            onNameChange = { },
            introductionState = TextUiState.default(100),
            onIntroductionChange = { },
            uploadEnabled = true,
            onUpload = { },
        )
    }
}
