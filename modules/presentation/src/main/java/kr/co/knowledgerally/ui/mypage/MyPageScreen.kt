package kr.co.knowledgerally.ui.mypage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.ContainedBadge
import kr.co.knowledgerally.ui.component.HorizontalSpacer
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyPageScreen(viewModel: MyPageViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // TopAppBar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(36.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = KnowllyTheme.colors.grayEF
                ),
                color = Color.Unspecified,
                modifier = Modifier
                    .height(28.dp)
                    .width(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(36.dp))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_ball),
                        tint = Color.Unspecified,
                        contentDescription = null
                    )
                    HorizontalSpacer(width = 4.dp)
                    Text(text = "1", style = KnowllyTheme.typography.subtitle4)
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)
                        .clickable { },
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_alarm),
                contentDescription = null,
                tint = KnowllyTheme.colors.gray00,
                modifier = Modifier
                    .padding(start = 16.dp, end = 24.dp)
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable { /* TODO */ }
                    .padding(4.dp)
            )
        }

        // Profile Content
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    top = 12.dp,
                    bottom = 40.dp,
                    start = 24.dp
                )
        ) {
            Image(
                painter = painterResource(R.drawable.img_avatar),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(60.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "유지민", style = KnowllyTheme.typography.subtitle1)

                Row(modifier = Modifier.padding(top = 4.dp)) {
                    ContainedBadge(
                        text = "플레이어",
                        contentColor = KnowllyTheme.colors.primaryDark,
                        backgroundColor = KnowllyTheme.colors.primary.copy(alpha = 0.1f)
                    )
                    ContainedBadge(
                        text = "코치",
                        contentColor = KnowllyTheme.colors.secondaryLimeDark,
                        backgroundColor = KnowllyTheme.colors.secondary.copy(alpha = 0.2f),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                KnowllyContainedButton(
                    text = "프로필 보기 / 수정",
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(40.dp)
                )
            }
        }

        Divider(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .height(1.dp),
            color = KnowllyTheme.colors.grayEF
        )

        Row(modifier = Modifier.padding(vertical = 20.dp, horizontal = 24.dp)) {
            Text(
                text = "알림 허용",
                style = KnowllyTheme.typography.subtitle4,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = false,
                onCheckedChange = null,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = KnowllyTheme.colors.primaryDark,
                    checkedTrackAlpha = 0.38f,
                    uncheckedThumbColor = KnowllyTheme.colors.primaryLight,
                    uncheckedTrackColor = KnowllyTheme.colors.primaryLight,
                    uncheckedTrackAlpha = 0.38f
                )
            )
        }
        Divider(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .height(1.dp),
            color = KnowllyTheme.colors.grayEF
        )

        Row(modifier = Modifier.padding(vertical = 20.dp, horizontal = 24.dp)) {
            Text(
                text = "앱 버전 정보",
                style = KnowllyTheme.typography.subtitle4,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "1.2.3",
                style = KnowllyTheme.typography.body1,
            )
        }
        Divider(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .height(1.dp),
            color = KnowllyTheme.colors.grayEF
        )

        Row(modifier = Modifier.padding(vertical = 20.dp, horizontal = 24.dp)) {
            Text(
                text = "서비스 이용약관 및 정책",
                style = KnowllyTheme.typography.subtitle4,
                modifier = Modifier.weight(1f)
            )
        }
        Divider(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .height(1.dp),
            color = KnowllyTheme.colors.grayEF
        )

        Row(modifier = Modifier.padding(vertical = 20.dp, horizontal = 24.dp)) {
            Text(
                text = "로그아웃",
                style = KnowllyTheme.typography.subtitle4,
                modifier = Modifier.weight(1f)
            )
        }
        Divider(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .height(1.dp),
            color = KnowllyTheme.colors.grayEF
        )

        Row(modifier = Modifier.padding(vertical = 20.dp, horizontal = 24.dp)) {
            Text(
                text = "회원 탈퇴",
                style = KnowllyTheme.typography.subtitle4,
                modifier = Modifier.weight(1f)
            )
        }
        Divider(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .height(1.dp),
            color = KnowllyTheme.colors.grayEF
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPageScreenPreview() {
    KnowllyTheme {
        MyPageScreen(MyPageViewModel())
    }
}
