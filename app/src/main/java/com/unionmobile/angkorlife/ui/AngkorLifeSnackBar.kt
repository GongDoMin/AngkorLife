package com.unionmobile.angkorlife.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.feature.common.dpTextUnit

@Composable
fun AngkorLifeSnackBar(
    snackbarData: SnackbarData
) {
    Snackbar(
        modifier = Modifier
            .padding(16.dp),
        shape = CircleShape
    ) {
        Text(
            modifier = Modifier
                .padding(4.dp),
            text = snackbarData.visuals.message,
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.dpTextUnit
            ),
            color = Color.White
        )
    }
}