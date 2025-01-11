package com.unionmobile.angkorlife

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.unionmobile.angkorlife.feature.login.loginScreen
import com.unionmobile.angkorlife.feature.main.mainScreen
import com.unionmobile.angkorlife.feature.navigation.Routes
import com.unionmobile.angkorlife.feature.navigation.rememberAngkorLifeNavController

@Composable
fun AngkorLifeApp() {
    val navController = rememberAngkorLifeNavController()

    NavHost(
        navController = navController.navController,
        startDestination = Routes.LOGIN,
    ) {
        loginScreen()

        mainScreen()
    }

}