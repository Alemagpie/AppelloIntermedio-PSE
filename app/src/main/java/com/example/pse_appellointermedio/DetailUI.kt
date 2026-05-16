package com.example.pse_appellointermedio

import android.content.res.Configuration
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController

@Composable
fun DetailUI(navController: NavController, viewModel: GameViewModel, recordID: Long?) {
    BackHandler {
        navController.popBackStack()
    }

    val record = viewModel.dm.getRecordFromId(recordID) ?: return

    val configuration = LocalConfiguration.current
    val padding = if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        detailSidePadding_land
    } else {
        detailSidePadding_port
    }
    val maxColors = if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        20
    } else {
        12
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CountText(count = record.errorIndex, padding)

            SeqText(seq = styleStringFromLength(record.errorIndex + 1, shortenSequence(maxChar= maxColors, s= record.sequence)), padding)
        }
    }
}

@Composable
fun CountText(count: Int, padding : Dp) {
    Column(
        modifier = Modifier.padding(start= padding, end= padding)
    ) {
        Text(
            text = count.toString(),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize
            )
        )
    }
}

@Composable
fun SeqText(seq: AnnotatedString, padding : Dp) {
    Column(
        modifier = Modifier.padding(start= padding, end= padding)
    ) {
        Text(
            text = seq,
            style = TextStyle(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
    }
}