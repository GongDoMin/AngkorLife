package com.unionmobile.angkorlife.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.unionmobile.angkorlife.design.R
import com.unionmobile.angkorlife.feature.common.AngkorLifeTopBarWithContent
import com.unionmobile.angkorlife.feature.common.CopyRightText
import com.unionmobile.angkorlife.feature.common.EventCollect
import com.unionmobile.angkorlife.feature.login.LoginViewModel

@Composable
fun MainScreen(
    navigateToDetail: (candidateId: Int) -> Unit,
    navigateToLogin: () -> Unit,
    showSnackBar: (message: String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EventCollect(
        event = viewModel.event,
        lifecycleOwner = LocalLifecycleOwner.current,
    ) {
        when (it) {
            is MainViewModel.Event.ShowSnackBar -> showSnackBar(it.message)
            is MainViewModel.Event.ShowSnackBarAndNavigateToLogin -> {
                showSnackBar(it.message)
                navigateToLogin()
            }
        }
    }

    AngkorLifeTopBarWithContent(
        isBackButtonVisible = false,
        title = stringResource(R.string.top_bar_title)
    ) {
        val gridCount = remember { 2 }

        LazyVerticalGrid (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            columns = GridCells.Fixed(gridCount)
        ) {
            item(
                span = { GridItemSpan(gridCount) }
            ) {
                MainHeader(
                    modifier = Modifier
                        .fillMaxWidth(),
                    timer = uiState.timer
                )
            }

            item(
                span = { GridItemSpan(gridCount) }
            ) {
                MainVoteDescription(
                    modifier = Modifier
                        .fillMaxWidth()
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
            }

            item(
                span = { GridItemSpan(gridCount) }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 50.dp,
                            bottom = 40.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    CandidateTitle(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                }
            }

            items(
                count = uiState.candidates.size,
                key = { uiState.candidates[it].id }
            ) { index ->
                val columnModifier = remember(index) {
                    if (index % gridCount == 0) {
                        Modifier.padding(start = 19.dp, end = 5.dp)
                    } else if (index % gridCount == gridCount - 1) {
                        Modifier.padding(start = 5.dp, end = 19.dp)
                    } else {
                        Modifier.padding(start = 5.dp, end = 5.dp)
                    }
                }

                Column(
                    modifier = columnModifier
                ) {
                    Candidate(
                        modifier = Modifier
                            .fillMaxWidth(),
                        url = uiState.candidates[index].profileUrl,
                        id = uiState.candidates[index].id,
                        name = uiState.candidates[index].name,
                        voteCountString = uiState.candidates[index].voteCntString,
                        isVoted = uiState.candidates[index].isVoted,
                        onClickImage = navigateToDetail,
                        onClickVote = viewModel::vote
                    )

                    Spacer(modifier = Modifier.padding(vertical = 20.dp))
                }
            }

            item(
                span = { GridItemSpan(gridCount) }
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
        navigateToDetail = {},
        navigateToLogin = {},
        showSnackBar = {}
    )
}
