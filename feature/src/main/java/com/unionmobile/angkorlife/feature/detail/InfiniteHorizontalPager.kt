package com.unionmobile.angkorlife.feature.detail

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun InfiniteHorizontalPager(
    listSize: Int,
    intervalSeconds: Long,
    modifier: Modifier = Modifier,
    bottomContent: @Composable (currentPage: Int) -> Unit,
    pagerContent: @Composable PagerScope.(page: Int) -> Unit
) {
    val pageCount = if (listSize > 0) Int.MAX_VALUE else 0
    val initialPage = if (listSize > 0) pageCount / 2 - ((pageCount / 2) % listSize) else 0
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { pageCount }
    )
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    if (!isDragged) {
        LaunchedEffect(Unit) {
            while (true) {
                delay(intervalSeconds)
                val target = if (pagerState.currentPage == Int.MAX_VALUE) initialPage else pagerState.currentPage + 1
                pagerState.animateScrollToPage(page = target)
            }
        }
    }

    Column(
        modifier = modifier
    ){
        HorizontalPager(pagerState) { page ->
            pagerContent(page)
        }

        bottomContent(pagerState.currentPage)
    }
}

