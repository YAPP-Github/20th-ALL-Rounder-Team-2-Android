package kr.co.knowledgerally.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun HomeScreen() {
    Box(contentAlignment = Alignment.Center) {
        Text(text = "홈화면")
    }
}
