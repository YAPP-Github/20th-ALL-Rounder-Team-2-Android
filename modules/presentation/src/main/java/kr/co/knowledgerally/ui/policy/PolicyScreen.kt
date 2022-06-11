package kr.co.knowledgerally.ui.policy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun PolicyScreen() {
    Column {
        TermsTopAppBar()
        Column(
            modifier = Modifier.padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 0.dp)
        ) {
            TermsTitle()
        }
    }
}

@Composable
fun TermsTopAppBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun TermsTitle() {
    Text(
        text = stringResource(R.string.policy_title),
        style = KnowllyTheme.typography.headline3,
    )
}

@Preview
@Composable
private fun TermsScreenPreview() {
    KnowllyTheme {
        PolicyScreen()
    }
}
