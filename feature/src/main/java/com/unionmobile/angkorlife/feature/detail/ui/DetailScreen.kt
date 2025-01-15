package com.unionmobile.angkorlife.feature.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.unionmobile.angkorlife.design.BasicLineHeightStyle
import com.unionmobile.angkorlife.design.KantumruyFontFamily
import com.unionmobile.angkorlife.design.R
import com.unionmobile.angkorlife.domain.model.MimeType
import com.unionmobile.angkorlife.feature.common.AngkorLifeTopBarWithContent
import com.unionmobile.angkorlife.feature.common.ConfirmModal
import com.unionmobile.angkorlife.feature.common.CopyRightText
import com.unionmobile.angkorlife.feature.common.EventCollect
import com.unionmobile.angkorlife.feature.common.PainterImage
import com.unionmobile.angkorlife.feature.common.dpTextUnit
import com.unionmobile.angkorlife.feature.detail.presentation.DetailViewModel

@Composable
fun DetailScreen(
    popBackStack: () -> Unit,
    navigateToLogin: () -> Unit,
    showSnackBar: (message: String) -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val voteButtonHeight = remember { 84.dp }
    val horizontalPagerIntervalSeconds = remember { 3000L }

    EventCollect(
        event = viewModel.event,
        lifecycleOwner = LocalLifecycleOwner.current,
    ) {
        when (it) {
            is DetailViewModel.Event.ShowSnackBar -> showSnackBar(it.message)
            is DetailViewModel.Event.ShowSnackBarAndNavigateToMain -> {
                showSnackBar(it.message)
                popBackStack()
            }
            is DetailViewModel.Event.ShowSnackBarAndNavigateToLogin -> {
                showSnackBar(it.message)
                navigateToLogin()
            }
        }
    }

    AngkorLifeTopBarWithContent(
        isBackButtonVisible = true,
        title = stringResource(R.string.top_bar_title),
        onClickBackButton = popBackStack
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                InfiniteHorizontalPager(
                    pageCount = uiState.candidateDetail.profileInfoList.size,
                    intervalSeconds = horizontalPagerIntervalSeconds,
                    bottomContent = { currentPage ->
                        Column(
                            modifier = Modifier
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 10.dp
                                )
                        ) {
                            DotContainer(
                                dotCount = uiState.candidateDetail.profileInfoList.size,
                                currentPage = currentPage
                            )
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
                        MimeType.IMAGE_JPG -> {
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
                        .height(voteButtonHeight)
                        .background(Color.Black)
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(voteButtonHeight)
                    .align(Alignment.BottomCenter)
                    .padding(
                        top = 12.dp,
                        bottom = 24.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                enabled = !uiState.candidateDetail.voted,
                colors = ButtonColors(
                    containerColor = Color(0xFF4232D5),
                    contentColor = Color.White,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color(0xFF4232D5),
                ),
                onClick = viewModel::vote,
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                val text = if (uiState.candidateDetail.voted) {
                    stringResource(R.string.voted)
                } else {
                    stringResource(R.string.vote)
                }

                if (uiState.candidateDetail.voted) {
                    PainterImage(
                        modifier = Modifier
                            .size(24.dp),
                        res = R.drawable.img_voted
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                }

                Text(
                    text = text,
                    style = TextStyle(
                        fontFamily = KantumruyFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.dpTextUnit,
                        lineHeight = 24.dpTextUnit,
                        lineHeightStyle = BasicLineHeightStyle
                    )
                )
            }
        }

        if (uiState.isModal) {
            ConfirmModal(
                onDismissRequest = viewModel::dismissModal,
                title = stringResource(R.string.voting_completed_title),
                message = stringResource(R.string.voting_completed_message),
                confirmButtonText = stringResource(R.string.confirm_button_text),
                onClickConfirmButton = viewModel::dismissModal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        popBackStack = {},
        navigateToLogin = {},
        showSnackBar = {}
    )
}