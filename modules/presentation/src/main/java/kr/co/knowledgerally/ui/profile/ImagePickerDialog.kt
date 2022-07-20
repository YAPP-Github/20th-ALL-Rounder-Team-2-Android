package kr.co.knowledgerally.ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kr.co.knowledgerally.ui.component.KnowllyDivider
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ImagePickerDialog(
    isVisible: Boolean,
    showGallery: () -> Unit,
    removeImage: () -> Unit,
    onDismiss: () -> Unit,
) {
    if (isVisible) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 14.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            "사진 선택",
                            style = KnowllyTheme.typography.subtitle1,
                        )
                    }
                    KnowllyDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showGallery() }
                            .padding(vertical = 18.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            "앨범에서 사진 선택하기",
                            style = KnowllyTheme.typography.subtitle3,
                        )
                    }
                    KnowllyDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { removeImage() }
                            .padding(vertical = 18.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            "기본 이미지로 변경",
                            style = KnowllyTheme.typography.subtitle3,
                        )
                    }
                }
            }
        }
    }
}
