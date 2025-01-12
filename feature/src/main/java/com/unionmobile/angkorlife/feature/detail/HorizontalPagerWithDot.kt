package com.unionmobile.angkorlife.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.unionmobile.angkorlife.domain.model.MimeType
import com.unionmobile.angkorlife.feature.detail.model.ProfileInfoModel
import kotlinx.coroutines.delay

@Composable
fun HorizontalPagerWithDot(
    profiles: List<ProfileInfoModel>,
    modifier: Modifier = Modifier
) {
    val size = if (profiles.isNotEmpty()) Int.MAX_VALUE else 0
    val initialPage = if (profiles.isNotEmpty()) size / 2 - (size / 2) % profiles.size else 0
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { size }
    )
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    if (!isDragged) {
        LaunchedEffect(Unit) {
            while (true) {
                delay(3000L)
                val target = if (pagerState.currentPage == Int.MAX_VALUE) initialPage else pagerState.currentPage + 1
                pagerState.animateScrollToPage(page = target)
            }
        }
    }

    Column(
        modifier = modifier
    ){
        HorizontalPager(pagerState) { page ->
            val horizontalModifier = remember {
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            }
            val profile = profiles[page % profiles.size]

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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 10.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            profiles.indices.forEach {
                val color =
                    if (it == (pagerState.currentPage % profiles.size)) {
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

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun HorizontalPagerWithDotPreview() {
    HorizontalPagerWithDot(
        profiles = listOf(ProfileInfoModel(), ProfileInfoModel())
    )
}

