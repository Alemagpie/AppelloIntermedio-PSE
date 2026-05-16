package com.example.pse_appellointermedio

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavController
import com.example.pse_appellointermedio.ui.theme.startGameBtn

@Composable
fun ListUI(navController: NavController, viewModel: GameViewModel) {

    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = listTopPadding_port),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GamesList_land(
                navController,
                viewModel
            )

            StartButton_land(
                navController,
                viewModel
            )
        }

    } else {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = listTopPadding_port),
                horizontalArrangement = Arrangement.Center
            ) {
                GamesList_port(
                    navController,
                    viewModel
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                StartButton_port(
                    navController,
                    viewModel
                )
            }
        }

    }
}

@Composable
fun GamesList_port(navController: NavController, viewModel : GameViewModel) {
    val gamesList = viewModel.gamesList
    LazyColumn (
        modifier = Modifier
            .size(width = listSizeX_port, height = listSizeY_port)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(8.dp))
    )
    {
        items(gamesList.size) { i ->
            GamesListItem(
                navController = navController,
                record = gamesList[i]
            )
        }
    }
}

@Composable
fun GamesList_land(navController: NavController, viewModel : GameViewModel) {
    val gamesList = viewModel.gamesList
    LazyColumn (
        modifier = Modifier
            .size(width = listSizeX_port, height = listSizeY_port)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(8.dp))
    )
    {
        items(gamesList.size) { i ->
            GamesListItem(
                navController = navController,
                record = gamesList[i]
            )
        }
    }
}

@Composable
fun GamesListItem(navController: NavController, record : GameRecord) {
    val pressedCount = record.errorIndex.toString()
    val pressedSeq = styleStringFromLength(record.errorIndex + 1, shortenSequence(s= record.sequence))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    navController.navigate("detail/${record.id}")
                }
            )
    ) {
        Text (
            modifier = Modifier
                .padding(listItemPadding),
            text = pressedCount,
            textAlign = TextAlign.Right
        )

        Text(
            modifier = Modifier
                .padding(listItemPadding),
            text = pressedSeq
        )
    }
}

@Composable
fun StartButton_port(navController: NavController, viewModel : GameViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = startGameButtonTopPadding_port),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {
                viewModel.resetState()
                navController.navigate("colorGrid")
            },
            colors = ButtonDefaults.buttonColors(containerColor = startGameBtn),
            modifier = Modifier.size(
                width = startGameButtonWidth_port,
                height = startGameButtonHeight_port
            )
        ) {
            Text(stringResource(R.string.startBtn))
        }

    }
}

@Composable
fun StartButton_land(navController: NavController, viewModel : GameViewModel) {
    Row(
        modifier = Modifier.padding(top= startGameButtonTopPadding_land)
    ) {
        Button(
            onClick = {
                viewModel.resetState()
                navController.navigate("colorGrid")
            },
            colors = ButtonDefaults.buttonColors(containerColor = startGameBtn),
            modifier = Modifier.size(
                width = startGameButtonWidth_land,
                height = startGameButtonHeight_land
            )
        ) {
            Text(stringResource(R.string.startBtn))
        }
    }
}

@Composable
fun LanguageIcon() {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    var currentLang by rememberSaveable { mutableStateOf(prefs.getString("lang", "en") ?: "en") }

    Image(
        painter = painterResource(R.drawable.languages_icon),
        contentDescription = stringResource(R.string.langIcon),
        modifier = Modifier
            .padding(start = langIconPadding, top = langIconPadding)
            .size(langIconSize)
            .clickable {
                currentLang = when(currentLang) {
                    "en" -> "it"
                    "it" -> "es"
                    "es" -> "en"
                    else -> "en"
                }
                prefs.edit { putString("lang", currentLang) }
                setLanguage(context, currentLang)
                context.startActivity(Intent(context, MainActivity::class.java))
                (context as Activity).finish()
            }
    )
}