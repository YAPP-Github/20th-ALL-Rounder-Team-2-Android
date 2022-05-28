package kr.co.knowledgerally.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = KnowllyTheme.colors.primaryDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            tint = KnowllyTheme.colors.grayFF,
            contentDescription = null,
            modifier = Modifier.size(width = 65.dp, height = 64.dp)
        )
        Spacer(Modifier.height(23.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_logo_text),
            tint = KnowllyTheme.colors.grayFF,
            contentDescription = null,
            modifier = Modifier.size(width = 128.dp, height = 35.dp)
        )
    }
}
