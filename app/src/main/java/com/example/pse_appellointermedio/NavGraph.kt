package com.example.pse_appellointermedio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    //Launches a coroutine that isn't UI related
    //Loads back up the recovery save, if left in the state to do so
    LaunchedEffect(Unit) {
        if (viewModel.shouldRecover()) {
            viewModel.loadRecoveryState()
            navController.navigate("colorGrid")
        }
    }

    NavHost(navController = navController, startDestination = "gamesList") {
        composable("gamesList") {
            ListUI(navController = navController, viewModel = viewModel)
        }
        composable("colorGrid") {
            MainUI(navController = navController, viewModel = viewModel)
        }
        composable("detail/{ID}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("ID")?.toLongOrNull()
            DetailUI(navController = navController, viewModel = viewModel, recordID = id)
        }
    }
}