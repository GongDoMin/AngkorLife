package com.unionmobile.angkorlife.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.design.R
import com.unionmobile.angkorlife.feature.common.AngkorLifeTopBarWithContent
import com.unionmobile.angkorlife.feature.common.CopyRightText

@Composable
fun MainScreen(
    navigateToDetail: (candidateId: Int) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AngkorLifeTopBarWithContent(
        isBackButtonVisible = false,
        title = stringResource(R.string.top_bar_title)
    ) {
        LazyVerticalGrid (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            columns = GridCells.Fixed(2)
        ) {
            item(
                span = { GridItemSpan(2) }
            ) {
                Column {
                    MainHeader(
                        timer = uiState.timer
                    )

                    MainVoteDescription(
                        modifier = Modifier
                            .drawBehind {
                                drawRect(
                                    Brush.linearGradient(
                                        colors = listOf(
                                            Color.Black,
                                            Color(0xFF161616)
                                        )
                                    )
                                )
                            }
                            .padding(
                                vertical = 50.dp,
                                horizontal = 16.dp
                            )
                    )

                    Spacer(
                        modifier = Modifier.padding(vertical = 25.dp)
                    )

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
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
                                fontSize = 28.sp,
                                lineHeight = 29.sp
                            ),
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.padding(vertical = 12.5.dp))

                        Text(
                            text = "\u203B You can vote for up to 3 candidates",
                            style = TextStyle(
                                fontFamily = KantumruyFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                lineHeight = 18.sp
                            ),
                            color = Color(0xFFAEAEB2)
                        )

                        Spacer(modifier = Modifier.padding(vertical = 20.dp))
                    }
                }
            }

            items(
                count = uiState.candidates.size,
                key = { uiState.candidates[it].id }
            ) { index ->
                val columnModifier = remember(index) {
                    if (index % 2 == 0) {
                        Modifier.padding(start = 19.dp, end = 5.dp)
                    } else {
                        Modifier.padding(start = 5.dp, end = 19.dp)
                    }
                }

                Column(
                    modifier = columnModifier
                ) {
                    Row {
                        Candidate(
                            url = uiState.candidates[index].profileUrl,
                            id = uiState.candidates[index].id,
                            name = uiState.candidates[index].name,
                            voteCountString = uiState.candidates[index].voteCntString,
                            isVoted = uiState.candidates[index].isVoted,
                            onClickImage = navigateToDetail,
                            onClickVote = viewModel::vote
                        )
                    }

                    Spacer(modifier = Modifier.padding(vertical = 20.dp))
                }
            }

            item(
                span = { GridItemSpan(2) }
            ) {
                CopyRightText(
                    modifier = Modifier
                        .padding(
                            top = 21.5.dp,
                            bottom = 22.5.dp
                        )

                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        navigateToDetail = {}
    )
}
