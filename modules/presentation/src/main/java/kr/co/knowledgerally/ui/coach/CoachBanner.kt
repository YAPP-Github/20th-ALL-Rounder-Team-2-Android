package kr.co.knowledgerally.ui.coach

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

/**
 * TODO: 배너 스타일 변경
 */
@Composable
fun CoachBanner(text: String) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFFF5F5F5),
        modifier = Modifier.padding(start = 24.dp, top = 12.dp, end = 24.dp),
    ) {
        Text(
            text = text,
            style = KnowllyTheme.typography.body2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp)
        )
    }
}
