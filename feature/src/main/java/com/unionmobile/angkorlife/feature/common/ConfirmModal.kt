package com.unionmobile.angkorlife.feature.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.unionmobile.angkorlife.design.BasicLineHeightStyle
import com.unionmobile.angkorlife.design.KantumruyFontFamily

@Composable
fun ConfirmModal(
    onDismissRequest: () -> Unit,
    title: String,
    message: String,
    confirmButtonText: String,
    onClickConfirmButton: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        )
    ) {
        Column(
            modifier = Modifier
                .width(280.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = title,
                style = TextStyle(
                    fontFamily = KantumruyFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.dpTextUnit,
                    lineHeight = 24.dpTextUnit,
                    lineHeightStyle = BasicLineHeightStyle,
                    color = Color.Black
                )
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = message,
                style = TextStyle(
                    fontFamily = KantumruyFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.dpTextUnit,
                    lineHeight = 20.dpTextUnit,
                    lineHeightStyle = BasicLineHeightStyle,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.padding(vertical = 9.dp))

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE5E5EA))
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onClickConfirmButton
                    ),
                text = confirmButtonText,
                style = TextStyle(
                    fontFamily = KantumruyFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.dpTextUnit,
                    lineHeight = 44.dpTextUnit,
                    lineHeightStyle = BasicLineHeightStyle,
                    color = Color(0xFF4232D5)
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ConfirmModalPreview() {
    ConfirmModal(
        onDismissRequest = {},
        title = "Voting completed",
        message = "Thank you for voting",
        confirmButtonText = "confirm",
        onClickConfirmButton = {}
    )
}
