package com.unionmobile.angkorlife.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.unionmobile.angkorlife.feature.login.loginScreen
import com.unionmobile.angkorlife.feature.main.mainScreen
import com.unionmobile.angkorlife.feature.navigation.Routes
import com.unionmobile.angkorlife.feature.navigation.rememberAngkorLifeNavController

@Composable
fun AngkorLifeApp() {
    val navController = rememberAngkorLifeNavController()

    Scaffold { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController.navController,
            startDestination = Routes.LOGIN,
        ) {
            loginScreen()

            mainScreen()
        }
    }
}