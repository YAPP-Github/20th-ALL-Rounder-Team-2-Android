package kr.co.knowledgerally.ui.mypage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.NavigationType
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun MyPageTopAppBar(ballCount: Int) {
    KnowllyTopAppBar(
        navigationType = NavigationType.None,
        actions = {
            Surface(
                shape = RoundedCornerShape(36.dp),
                color = Color.Unspecified,
                border = BorderStroke(width = 2.dp, color = KnowllyTheme.colors.grayEF),
                modifier = Modifier
                    .clip(RoundedCornerShape(36.dp))
                    .clickable { }
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        4.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ball),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = ballCount.toString(), style = KnowllyTheme.typography.subtitle4
                    )
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_alarm),
                contentDescription = null,
                tint = KnowllyTheme.colors.gray00,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable { }
                    .padding(4.dp)
            )
        }
    )
}