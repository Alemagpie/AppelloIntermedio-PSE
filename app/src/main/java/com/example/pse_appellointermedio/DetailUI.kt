package com.example.pse_appellointermedio

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun DetailUI(modifier: Modifier = Modifier, navController: NavController) {
    BackHandler() {
        navController.popBackStack()
    }
}