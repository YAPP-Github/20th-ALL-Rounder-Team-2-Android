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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyTextField
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            VerticalSpacer(height = 56.dp)
            Text(
                text = "프로필을 설정해주세요.",
                modifier = Modifier.padding(top = 8.dp),
                style = KnowllyTheme.typography.headline4,
            )
            VerticalSpacer(height = 36.dp)

            Box(
                modifier = Modifier
                    .size(156.dp)
                    .align(Alignment.CenterHorizontally),
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

            VerticalSpacer(height = 16.dp)
            Text(text = "이름", style = KnowllyTheme.typography.headline4)
            VerticalSpacer(height = 12.dp)

            KnowllyTextField(
                value = "",
                onValueChange = { },
                placeHolderText = "이름을 입력하세요",
                helperText = "message",
                helperTextEnabled = true,
                counterMaxLength = 10,
                counterEnabled = true,
            )

            VerticalSpacer(height = 20.dp)
            Text(text = "자기소개", style = KnowllyTheme.typography.headline4)
            VerticalSpacer(height = 12.dp)

            KnowllyTextField(
                value = "",
                onValueChange = { },
                placeHolderText = "자기소개를 자세히 기록해주세요",
                helperText = "message",
                singleLine = false,
                counterMaxLength = 100,
                counterEnabled = true,
                minHeight = 180.dp
            )

            // Button Size + Button Padding
            VerticalSpacer(height = 90.dp)
        }

        KnowllyContainedButton(
            text = "완료",
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(bottom = 24.dp, top = 20.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    KnowllyTheme {
        ProfileScreen(viewModel = ProfileViewModel())
    }
}
