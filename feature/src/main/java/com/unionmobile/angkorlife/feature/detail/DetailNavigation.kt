package com.unionmobile.angkorlife.feature.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.unionmobile.angkorlife.feature.navigation.Routes

fun NavGraphBuilder.detailScreen(
    popBackStack: () -> Unit,
    navigateToLogin: () -> Unit,
    showSnackBar: (message: String) -> Unit
) {
    composable<Routes.DETAIL> {
        DetailScreen(
            popBackStack = popBackStack,
            navigateToLogin = navigateToLogin,
            showSnackBar = showSnackBar
        )
    }
}