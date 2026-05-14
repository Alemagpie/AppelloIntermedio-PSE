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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController

@Composable
fun DetailUI(modifier: Modifier = Modifier, navController: NavController, viewModel: GameViewModel, recordID: Long?) {
    BackHandler {
        navController.popBackStack()
    }

    val record = viewModel.dm.getRecordFromId(recordID) ?: return

    val configuration = LocalConfiguration.current
    val countPadding: Dp
    val seqPadding: Dp

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        countPadding = detailCountTopPadding_land
        seqPadding = detailSeqTopPadding_land
    } else {
        countPadding = detailCountTopPadding_port
        seqPadding = detailSeqTopPadding_port
    }

    Column(modifier = Modifier.fillMaxSize()) {
        CountText(count = record.errorIndex, topPadding = countPadding)
        SeqText(seq = styleStringFromLength(record.errorIndex + 1, record.sequence), topPadding = seqPadding)
    }
}

@Composable
fun CountText(modifier: Modifier = Modifier, count: Int, topPadding: Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topPadding),
        horizontalArrangement = Arrangement.Center
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
fun SeqText(modifier: Modifier = Modifier, seq: AnnotatedString, topPadding: Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topPadding),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = seq,
            style = TextStyle(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}