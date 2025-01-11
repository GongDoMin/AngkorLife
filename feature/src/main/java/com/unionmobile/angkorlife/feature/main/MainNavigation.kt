package com.unionmobile.angkorlife.feature.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.unionmobile.angkorlife.feature.navigation.Routes

fun NavGraphBuilder.mainScreen() {
    composable<Routes.MAIN> {
        MainScreen(
            candidates = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
        )
    }
}