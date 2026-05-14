package com.example.pse_appellointermedio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.pse_appellointermedio.ui.theme.PSEAppelloIntermedioTheme


val titleFontSize = 20.sp
val mainTopPadding_port = 70.dp
val titleTopPadding_land = 35.dp
val contentTopPadding_land = 70.dp
val btnSize_port = 100.dp
val btnSize_land = 75.dp
val btnSpacing = 10.dp
val btnRadius = 8.dp
val matrixTopPadding_port = 80.dp
val matrixTopPadding_land = 30.dp
val matrixLeftPadding_land = 150.dp
val seqTextTopPadding_port = 20.dp
val seqTextTopPadding_land = 30.dp
val seqTextHeight_port = 80.dp
val seqTextHeight_land = 30.dp
val startButtonTopPadding_port = 100.dp
val startButtonTopPadding_land = 50.dp
val actionButtonsWidth_port = 160.dp
val actionButtonsWidth_land = 250.dp
val actionButtonsHeight_port = 80.dp
val actionButtonsHeight_land = 50.dp
val actionButtonsTopPadding_port = 50.dp
val actionButtonsUpperPadding_land = 10.dp
val actionButtonsMiddlePadding_land = 20.dp
val startGameButtonTopPadding_port = 30.dp
val startGameButtonTopPadding_land = 50.dp
val startGameButtonWidth_port = 250.dp
val startGameButtonWidth_land = 250.dp
val startGameButtonHeight_port = 50.dp
val startGameButtonHeight_land = 50.dp
val listTopPadding_port = 60.dp
val listSizeX_port = 200.dp
val listSizeY_port = 300.dp
val listItemPadding = 15.dp
val langIconSize = 40.dp
val langIconPadding = 20.dp
val detailCountTopPadding_port = 240.dp
val detailCountTopPadding_land = 90.dp
val detailSeqTopPadding_port = 100.dp
val detailSeqTopPadding_land = 50.dp


//-----------------------------------------------


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PSEAppelloIntermedioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()

        val viewModel = ViewModelProvider(this)[GameViewModel::class.java]

        if(isFinishing) {
            //User killed the activity, so clear the saved states
            viewModel.resetRecoveryState()
        } else {
            //Android is killing the activity, save variables and setup for recovery on reopening
            viewModel.setRecoveryState()
        }
    }
}