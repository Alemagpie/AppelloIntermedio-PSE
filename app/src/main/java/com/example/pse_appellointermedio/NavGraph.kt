package com.example.pse_appellointermedio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    //ViewModel that holds states, sequences and coroutines
    //It had to be moved here instead of getting it from the various UI functions because they would get different instances and cause sync problems
    val viewModel: GameViewModel = viewModel()

    NavHost(navController = navController, startDestination = "gamesList") {
        composable("gamesList") {
            ListUI(navController = navController, viewModel = viewModel)
        }
        composable("colorGrid") {
            viewModel.resetState()
            MainUI(navController = navController, viewModel = viewModel)
        }
        composable("detail/{game}") { backStackEntry ->
            val game = backStackEntry.arguments?.getString("game")
            DetailUI(navController = navController, data="")
        }
    }
}