package com.unionmobile.angkorlife.feature.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.unionmobile.angkorlife.feature.navigation.Routes

fun NavGraphBuilder.mainScreen() {
    composable<Routes.MAIN> {
        mainScreen()
    }
}