package com.unionmobile.angkorlife.ui

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.unionmobile.angkorlife.feature.detail.detailScreen
import com.unionmobile.angkorlife.feature.login.navigation.loginScreen
import com.unionmobile.angkorlife.feature.main.navigation.mainScreen
import com.unionmobile.angkorlife.feature.navigation.Routes
import com.unionmobile.angkorlife.feature.navigation.rememberAngkorLifeNavController
import kotlinx.coroutines.launch

@Composable
fun AngkorLifeApp() {
    val angkorLifeNavController = rememberAngkorLifeNavController()
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier
            .imePadding(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = {
                    AngkorLifeSnackBar(it)
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = angkorLifeNavController.navController,
            startDestination = Routes.LOGIN,
        ) {
            loginScreen(
                navigateToMain = angkorLifeNavController::navigateToMain,
                showSnackBar = {
                    coroutineScope.launch {
                        snackBarHostState.dismissAndShow(message = it)
                    }
                }
            )

            mainScreen(
                navigateToDetail = angkorLifeNavController::navigateToDetail,
                navigateToLogin = angkorLifeNavController::navigateToLogin,
                showSnackBar = {
                    coroutineScope.launch {
                        snackBarHostState.dismissAndShow(message = it)
                    }
                }
            )

            detailScreen(
                popBackStack = angkorLifeNavController::popBackStack,
                navigateToLogin = angkorLifeNavController::navigateToLogin,
                showSnackBar = {
                    coroutineScope.launch {
                        snackBarHostState.dismissAndShow(message = it)
                    }
                }
            )
        }
    }
}


private suspend fun SnackbarHostState.dismissAndShow(
    message: String,
    duration: SnackbarDuration = SnackbarDuration.Short
) {
    currentSnackbarData?.dismiss()
    showSnackbar(
        message = message,
        duration = duration
    )
}