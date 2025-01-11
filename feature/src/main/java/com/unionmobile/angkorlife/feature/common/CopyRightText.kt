package com.unionmobile.angkorlife.feature.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.unionmobile.angkorlife.design.RobotoFontFamily

@Composable
fun CopyRightText(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        modifier = modifier,
        text = "COPYRIGHT © WUPSC ALL RIGHT RESERVED.",
        style = TextStyle(
            fontFamily = RobotoFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 10.sp,
        ),
        color = Color(0xFFF8F8Fc),
        textAlign = textAlign
    )
}

@Preview
@Composable
fun CopyRightTextPreview() {
    CopyRightText()
}