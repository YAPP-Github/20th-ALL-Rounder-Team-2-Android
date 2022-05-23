package kr.co.knowledgerally.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kr.co.knowledgerally.ui.component.KnowllyOutlinedButton

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        KnowllyOutlinedButton(
            text = "카카오 로그인",
            onClick = { onLogin() }
        )
    }
}
