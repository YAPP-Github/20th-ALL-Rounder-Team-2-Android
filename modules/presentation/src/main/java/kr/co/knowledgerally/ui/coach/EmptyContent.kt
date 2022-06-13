package kr.co.knowledgerally.ui.coach

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun EmptyContent(navigateToRegister: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(ROOT_PADDING)
        ) {
            Text(
                text = stringResource(id = R.string.coach_empty_title),
                modifier = Modifier.padding(top = 12.dp),
                style = KnowllyTheme.typography.headline3,
            )
            Image(
                painter = painterResource(id = R.drawable.img_knowlly_onboarding_illust_3),
                contentDescription = null,
                modifier = Modifier.padding(vertical = 24.dp),
                contentScale = ContentScale.FillWidth,
            )
            Text(
                text = stringResource(id = R.string.coach_empty_subtitle),
                style = KnowllyTheme.typography.subtitle2,
            )
            Text(
                text = stringResource(id = R.string.coach_empty_description),
                modifier = Modifier.padding(top = 12.dp),
                style = KnowllyTheme.typography.body1,
                color = KnowllyTheme.colors.gray6B,
            )
        }
        KnowllyContainedButton(
            text = stringResource(id = R.string.coach_empty_button),
            onClick = navigateToRegister,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
    }
}

private val ROOT_PADDING = PaddingValues(
    start = 24.dp,
    top = 12.dp,
    end = 24.dp,
    bottom = 90.dp
)

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun EmptyContentPreview() {
    KnowllyTheme {
        EmptyContent(
            navigateToRegister = { },
        )
    }
}
