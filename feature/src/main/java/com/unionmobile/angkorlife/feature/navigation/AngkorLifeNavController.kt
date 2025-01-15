package com.unionmobile.angkorlife.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAngkorLifeNavController(
    navController: NavHostController = rememberNavController()
) : AngkorLifeNavController = remember(navController) {
    AngkorLifeNavController(navController)
}


@Stable
class AngkorLifeNavController(
    val navController: NavHostController
) {
    fun popBackStack() {
        navController.popBackStack()
    }

    fun navigateToLogin() {
        navController.navigate(Routes.LOGIN) {
            popUpTo(navController.graph.id) {
                inclusive = false
            }
        }
    }

    fun navigateToMain() {
        navController.navigate(Routes.MAIN) {
            popUpTo(Routes.LOGIN) {
                inclusive = true
            }
        }
    }

    fun navigateToDetail(candidateId: Int, voteCnt: Int) {
        navController.navigate(Routes.DETAIL(candidateId, voteCnt))
    }
}