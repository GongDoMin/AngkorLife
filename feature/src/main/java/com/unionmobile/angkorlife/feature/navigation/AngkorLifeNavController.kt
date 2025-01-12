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
    fun navigateToMain() {
        navController.navigate(Routes.MAIN) {
            popUpTo(Routes.LOGIN) {
                inclusive = true
            }
        }
    }

    fun navigateToDetail(candidateId: Int) {
        navController.navigate(Routes.DETAIL(candidateId))
    }
}