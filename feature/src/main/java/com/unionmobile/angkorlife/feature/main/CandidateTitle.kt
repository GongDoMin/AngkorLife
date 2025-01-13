package com.unionmobile.angkorlife.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun CandidateTitle(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF6F76FF))
                .size(
                    width = 19.dp,
                    height = 3.dp
                )
        )

        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        Text(
            text = "2024\nCandidate List",
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.dpTextUnit,
                lineHeight = 29.dpTextUnit
            ),
            color = Color.White
        )

        Spacer(modifier = Modifier.padding(vertical = 12.5.dp))

        Text(
            text = "\u203B You can vote for up to 3 candidates",
            style = TextStyle(
                fontFamily = KantumruyFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.dpTextUnit,
                lineHeight = 18.dpTextUnit
            ),
            color = Color(0xFFAEAEB2)
        )
    }
}