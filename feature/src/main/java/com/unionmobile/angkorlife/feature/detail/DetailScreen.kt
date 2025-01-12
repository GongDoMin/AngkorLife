package com.unionmobile.angkorlife.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unionmobile.angkorlife.design.R
import com.unionmobile.angkorlife.feature.common.AngkorLifeTopBarWithContent
import com.unionmobile.angkorlife.feature.common.CopyRightText

@Composable
fun DetailScreen() {
    AngkorLifeTopBarWithContent(
        isBackButtonVisible = true,
        title = stringResource(R.string.top_bar_title)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(Color.Black)
                    .verticalScroll(rememberScrollState())
            ) {
                HorizontalPagerWithDot(
                    listOf(0, 1, 2, 3, 4, 5)
                )

                CandidateInformation(
                    modifier = Modifier
                        .background(Color(0xFF121212))
                        .padding(
                            vertical = 26.dp,
                            horizontal = 16.dp
                        )
                )

                CopyRightText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF070909))
                        .padding(
                            top = 21.5.dp,
                            bottom = 22.5.dp
                        )
                )

                Box(
                    modifier = Modifier
                        .height(84.dp)
                        .background(Color.Black)
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(84.dp)
                    .align(Alignment.BottomCenter)
                    .padding(
                        top = 12.dp,
                        bottom = 24.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                enabled = false,
                colors = ButtonColors(
                    containerColor = Color(0xFF4232D5),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF4232D5),
                    disabledContentColor = Color.White,
                ),
                onClick = { TODO("Not yet implemented") },
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                Text(text = "Vote")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen()
}