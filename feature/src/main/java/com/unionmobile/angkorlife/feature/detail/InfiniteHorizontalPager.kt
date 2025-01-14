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
    // listSize 가 0일 경우에는 pageCount 를 0으로 한다.
    val pageCount = if (listSize > 0) Int.MAX_VALUE else 0

    // initialPage는 전체 페이지 중간 지점을 기준으로 listSize의 배수인 페이지를 설정합니다.
    // 이렇게 함으로써, 무한 스크롤의 경우 첫 페이지가 정확히 리스트의 시작 상태와 매칭됩니다.
    val initialPage = if (listSize > 0) pageCount / 2 - ((pageCount / 2) % listSize) else 0

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { pageCount }
    )
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    // 드래그 중이라면 취소
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

