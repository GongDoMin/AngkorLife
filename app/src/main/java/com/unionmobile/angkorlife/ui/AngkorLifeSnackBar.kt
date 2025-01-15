package com.unionmobile.angkorlife.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.unionmobile.angkorlife.design.BasicLineHeightStyle
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.feature.common.dpTextUnit

@Composable
fun AngkorLifeSnackBar(
    snackbarData: SnackbarData
) {
    CustomSnackBarBox(
        shape = CircleShape
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp),
            text = snackbarData.visuals.message,
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.dpTextUnit,
                lineHeight = 16.dpTextUnit,
                lineHeightStyle = BasicLineHeightStyle
            )
        )
    }
}

@Composable
private fun CustomSnackBarBox(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    containerColor: Color = Color.DarkGray,
    contentColor: Color = Color.White,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalContentColor provides contentColor
    ) {
        Box(
            modifier = modifier
                .padding(16.dp)
                .background(color = containerColor, shape = shape)
                .clip(shape),
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}