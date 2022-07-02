package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun AddPhotoIcon(
    size: Dp,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = CircleShape,
        color = KnowllyTheme.colors.gray00,
        border = BorderStroke(1.dp, KnowllyTheme.colors.grayFF),
        modifier = modifier
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_a_photo),
                contentDescription = null,
                modifier = Modifier.size(size),
                tint = KnowllyTheme.colors.grayFF
            )
        }
    }
}
