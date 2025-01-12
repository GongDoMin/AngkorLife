package com.unionmobile.angkorlife.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.gif.AnimatedImageDecoder
import coil3.request.ImageRequest
import com.unionmobile.angkorlife.feature.detail.model.ProfileInfoModel

@Composable
fun HorizontalPagerWithDot(
    profiles: List<ProfileInfoModel>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(
        pageCount = { profiles.size }
    )

    Column(
        modifier = modifier
    ){
        HorizontalPager(pagerState) { page ->
            if (profiles[page].mimeType == "image/gif") {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context).data(profiles[page].profileUrl).build()
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    painter = painter,
                    contentDescription = null
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    model = profiles[page].profileUrl,
                    contentDescription = null
                )
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
                    if (it == pagerState.currentPage) {
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
        profiles = emptyList()
    )
}
