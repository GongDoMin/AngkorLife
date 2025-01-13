package com.unionmobile.angkorlife.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DotContainer(
    dotCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    val dotSize = remember { 8.dp }
    val dotBottomPadding = remember { 10.dp }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(dotSize + dotBottomPadding)
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = dotBottomPadding
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        repeat(dotCount) {
            val color =
                if (it == (currentPage % dotCount)) {
                    Color(0xFF6F76FF)
                } else {
                    Color.White
                }

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(8.dp)
                    .background(color)
            )
        }
    }
}