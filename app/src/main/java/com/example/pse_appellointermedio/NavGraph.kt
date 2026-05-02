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

    var allGames = rememberSaveable{ mutableListOf<String>() }

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainUI(navController = navController, gamesList = allGames, onAddGame = { entry -> allGames += entry })
        }
        composable("secondary") {
            SecondaryUI(navController = navController, gamesList = allGames)
        }
        composable("detail/{game}") { backStackEntry ->
            val game = backStackEntry.arguments?.getString("game")
            DetailUI(navController = navController)
        }
    }
}