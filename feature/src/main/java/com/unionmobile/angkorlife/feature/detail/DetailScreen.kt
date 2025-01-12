package com.unionmobile.angkorlife.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.unionmobile.angkorlife.design.R
import com.unionmobile.angkorlife.domain.model.MimeType
import com.unionmobile.angkorlife.feature.common.AngkorLifeTopBarWithContent
import com.unionmobile.angkorlife.feature.common.CopyRightText

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AngkorLifeTopBarWithContent(
        isBackButtonVisible = true,
        title = stringResource(R.string.top_bar_title)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val buttonHeight = remember { 84.dp }
            val horizontalPagerIntervalSeconds = remember { 3000L }

            Column(
                modifier = Modifier
                    .background(Color.Black)
                    .verticalScroll(rememberScrollState())
            ) {
                InfiniteHorizontalPager(
                    listSize = uiState.candidateDetail.profileInfoList.size,
                    intervalSeconds = horizontalPagerIntervalSeconds,
                    bottomContent = { currentPage ->
                        val dotSize = remember { 8.dp }
                        val bottomPadding = remember { 10.dp }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dotSize + bottomPadding)
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = bottomPadding
                                ),
                            horizontalArrangement = Arrangement.spacedBy(dotSize, Alignment.CenterHorizontally)
                        ) {
                            with(uiState.candidateDetail.profileInfoList) {
                                this.indices.forEach {
                                    val color =
                                        if (it == (currentPage % this.size)) {
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
                    }
                ) { currentPage ->
                    val horizontalModifier = remember {
                        Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    }
                    val profile = uiState.candidateDetail.profileInfoList[currentPage % uiState.candidateDetail.profileInfoList.size]

                    when (profile.mimeType) {
                        MimeType.IMAGE_GIF, MimeType.IMAGE_JPG, MimeType.IMAGE_PNG -> {
                            AsyncImage(
                                modifier = horizontalModifier,
                                model = profile.profileUrl,
                                contentDescription = null
                            )
                        }
                        else -> {
                            Box(
                                modifier = horizontalModifier
                                    .background(Color.Black),
                            )
                        }
                    }
                }

                CandidateInformation(
                    modifier = Modifier
                        .background(Color(0xFF121212))
                        .padding(
                            vertical = 26.dp,
                            horizontal = 16.dp
                        ),
                    name = uiState.candidateDetail.name,
                    candidateNumber = uiState.candidateDetail.candidateNumber,
                    education = uiState.candidateDetail.education,
                    major = uiState.candidateDetail.major,
                    hobby = uiState.candidateDetail.hobby,
                    talent = uiState.candidateDetail.talent,
                    ambition = uiState.candidateDetail.ambition,
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
                        .height(buttonHeight)
                        .background(Color.Black)
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonHeight)
                    .align(Alignment.BottomCenter)
                    .padding(
                        top = 12.dp,
                        bottom = 24.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                enabled = uiState.candidateDetail.voted,
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