package com.unionmobile.angkorlife.feature.detail.ui

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun InfiniteHorizontalPager(
    pageCount: Int,
    intervalSeconds: Long,
    modifier: Modifier = Modifier,
    bottomContent: @Composable (currentPage: Int) -> Unit,
    pagerContent: @Composable PagerScope.(currentPage: Int) -> Unit
) {
    val max = remember { 1000 }
    val totalPageCount = remember(pageCount) { pageCount * max }
    val half = totalPageCount / 2

    // initialPage는 전체 페이지 중간 지점을 기준으로 listSize의 배수인 페이지를 설정합니다.
    // 이렇게 함으로써, 무한 스크롤의 경우 첫 페이지가 정확히 리스트의 시작 상태와 매칭됩니다.
    val initialPage = remember(pageCount) {
        if (pageCount > 0) half - (half % pageCount)
        else 0
    }

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { totalPageCount }
    )
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(initialPage) {
        if (initialPage != 0) pagerState.scrollToPage(initialPage)
    }

    // 드래그 중이라면 취소
    if (!isDragged) {
        LaunchedEffect(Unit) {
            while (true) {
                delay(intervalSeconds)
                val target =
                    if (pagerState.currentPage == totalPageCount) initialPage
                    else pagerState.currentPage + 1
                pagerState.animateScrollToPage(page = target)
            }
        }
    }

    Column(
        modifier = modifier
    ){
        HorizontalPager(pagerState) { currentPage ->
            pagerContent(currentPage)
        }

        bottomContent(pagerState.currentPage)
    }
}

