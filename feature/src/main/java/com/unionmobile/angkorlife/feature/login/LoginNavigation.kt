package com.unionmobile.angkorlife.feature.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.unionmobile.angkorlife.feature.navigation.Routes

fun NavGraphBuilder.loginScreen() {
    composable<Routes.LOGIN> {
        LoginScreen()
    }
}