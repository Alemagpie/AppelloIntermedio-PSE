package com.example.pse_appellointermedio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "gamesList") {
        composable("gamesList") {
            ListUI(navController = navController)
        }
        composable("colorGrid") {
            MainUI(navController = navController)
        }
        composable("detail/{game}") { backStackEntry ->
            val game = backStackEntry.arguments?.getString("game")
            DetailUI(navController = navController)
        }
    }
}