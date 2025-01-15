package com.unionmobile.angkorlife.feature.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.unionmobile.angkorlife.feature.main.ui.MainScreen
import com.unionmobile.angkorlife.feature.navigation.Routes

fun NavGraphBuilder.mainScreen(
    navigateToDetail: (candidateId: Int, voteCnt: Int) -> Unit,
    navigateToLogin: () -> Unit,
    showSnackBar: (message: String) -> Unit
) {
    composable<Routes.MAIN> {
        MainScreen(
            navigateToDetail = navigateToDetail,
            navigateToLogin = navigateToLogin,
            showSnackBar = showSnackBar
        )
    }
}