package kr.co.knowledgerally.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun SplashPageItem(
    pageItem: PageItem,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = pageItem.image),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.BottomStart)
        ) {
            Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                Text(
                    text = stringResource(id = pageItem.title),
                    style = KnowllyTheme.typography.headline3,
                    color = KnowllyTheme.colors.gray00,
                )
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    pageItem.trailingTitle?.invoke()
                }
            }
            Text(
                text = stringResource(id = pageItem.description),
                modifier = Modifier.padding(top = 20.dp),
                style = KnowllyTheme.typography.subtitle3,
                color = KnowllyTheme.colors.gray6B,
            )
        }
    }
}
