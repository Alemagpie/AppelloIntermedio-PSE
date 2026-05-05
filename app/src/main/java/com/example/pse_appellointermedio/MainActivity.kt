package com.example.pse_appellointermedio

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pse_appellointermedio.ui.theme.PSEAppelloIntermedioTheme
import com.example.pse_appellointermedio.ui.theme.boxR
import com.example.pse_appellointermedio.ui.theme.boxG
import com.example.pse_appellointermedio.ui.theme.boxB
import com.example.pse_appellointermedio.ui.theme.boxM
import com.example.pse_appellointermedio.ui.theme.boxY
import com.example.pse_appellointermedio.ui.theme.boxC
import com.example.pse_appellointermedio.ui.theme.cancBtn
import com.example.pse_appellointermedio.ui.theme.fineBtn
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.pse_appellointermedio.ui.theme.backBtn
import com.example.pse_appellointermedio.ui.theme.listOutline_dark
import com.example.pse_appellointermedio.ui.theme.listOutline_light
import androidx.core.content.edit
import com.example.pse_appellointermedio.ui.theme.startBtn
import kotlinx.coroutines.*
import kotlin.random.Random


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
val seqTextTopPadding_port = 80.dp
val seqTextTopPadding_land = 30.dp
val startButtonTopPadding_port = 100.dp
val startButtonTopPadding_land = 50.dp
val actionButtonsWidth_port = 160.dp
val actionButtonsWidth_land = 250.dp
val actionButtonsHeight_port = 80.dp
val actionButtonsHeight_land = 50.dp
val actionButtonsTopPadding_port = 50.dp
val actionButtonsUpperPadding_land = 30.dp
val actionButtonsMiddlePadding_land = 20.dp
val backButtonTopPadding_port = 30.dp
val backButtonTopPadding_land = 50.dp
val backButtonWidth_port = 250.dp
val backButtonWidth_land = 250.dp
val backButtonHeight_port = 50.dp
val backButtonHeight_land = 50.dp
val listTopPadding_port = 60.dp
val listSizeX_port = 200.dp
val listSizeY_port = 300.dp
val listItemPadding = 15.dp
val langIconSize = 40.dp
val langIconPadding = 20.dp


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
}


@Composable
fun MainUI(modifier: Modifier = Modifier, navController: NavController, gamesList : List<String>, onAddGame: (String) -> Unit) {
    var sequenceString by rememberSaveable{ mutableStateOf("") }
    var proposedSequence by remember { mutableStateOf("") }
    var sequenceLength by remember{ mutableStateOf(0)}
    var inputLength by remember{ mutableStateOf(0)}
    var isPaused by rememberSaveable{ mutableStateOf(false) }
    var isShowingSequence by rememberSaveable{ mutableStateOf(false) }
    var hasStartedGame by rememberSaveable{ mutableStateOf(false) }
    val configuration = LocalConfiguration.current

    val scope = rememberCoroutineScope()
    var job by remember { mutableStateOf<Job?>(null) }

    var hIndex by remember { mutableStateOf<Int?>(null) }
    val startSequence : () -> Unit = {  //atm it stops if screen is rotated
        job = scope.launch {
            delay(500)
            inputLength = 0
            sequenceString = ""
            delay(200)

            isShowingSequence = true
            proposedSequence = addToRandomSequence(proposedSequence)
            val s = proposedSequence.replace(", ", "")
            sequenceLength++

            for (c in s) {
                hIndex = getIndexFromColor(c)
                delay(600)
                hIndex = null
                delay(200)
            }
            isShowingSequence = false
        }
    }

    val addAndCheckColor : (Int) -> Unit = {i ->
        if(!isShowingSequence) {
            sequenceString = appendColorToSequence(i, sequenceString)
            inputLength++

            if(sequenceString == proposedSequence.subSequence(0, sequenceString.length).toString()) {
                if(inputLength == sequenceLength)
                    startSequence()
                //Log.i("seq", "match")
            } else {
                //Error screen
                //.i("seq", "mismatch")

            }
        }
    }


    if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = Modifier
            .fillMaxWidth()
            .padding(top = titleTopPadding_land)
        ) {
            Title_land()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = contentTopPadding_land)
        ) {
            Column() {
                ColorGrid_land(
                    Modifier,
                    sequenceString,
                    onButtonClick = { index ->
                        if(!isShowingSequence) {
                            sequenceString = appendColorToSequence(index, sequenceString)
                        }
                    },
                    hIndex
                )
            }

            Column() {
                SequenceText_land(
                    Modifier,
                    sequenceString,
                    hasStartedGame
                )

                StartButton_land(
                    Modifier,
                    hasStartedGame,
                    startGame = {
                        hasStartedGame = true
                        startSequence()
                    }
                )

                ActionButtons_land(
                    Modifier,
                    sequenceString,
                    pauseGame = { isPaused = !isPaused },
                    isPaused,
                    isShowingSequence,
                    hasStartedGame,
                    navController,
                    onAddGame
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = mainTopPadding_port),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Title_port()

            ColorGrid_port(
                Modifier,
                sequenceString,
                onButtonClick = { index ->
                    addAndCheckColor(index)
                },
                hIndex
            )

            SequenceText_port(
                Modifier,
                sequenceString,
                hasStartedGame
            )

            StartButton_port(
                Modifier,
                hasStartedGame,
                startGame = {
                    hasStartedGame = true
                    startSequence()
                }
            )

            ActionButtons_port(
                Modifier,
                sequenceString,
                pauseGame = { isPaused = !isPaused },
                isPaused,
                isShowingSequence,
                hasStartedGame,
                navController,
                onAddGame
            )
        }
    }

    //ONLY for testing localization
    //LanguageIcon()
}

@Composable
fun SecondaryUI(modifier: Modifier = Modifier, navController: NavController, gamesList : List<String> ) {
    BackHandler() {
        navController.popBackStack()
    }

    val configuration = LocalConfiguration.current

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = listTopPadding_port),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GamesList_land(
                Modifier,
                gamesList
            )

            BackButton_land(
                Modifier,
                navController
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
                    Modifier,
                    gamesList
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                BackButton_port(
                    Modifier,
                    navController
                )
            }
        }

    }
}

@Composable
fun DetailUI(modifier: Modifier = Modifier, navController: NavController) {
    BackHandler() {
        navController.popBackStack()
    }


}
@Composable
fun Title_port(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.title),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize
            )
        )
    }
}

@Composable
fun Title_land(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.title),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize
            )
        )
    }
}

@Composable
fun ColorGrid_port(modifier: Modifier = Modifier, seqS : String, onButtonClick: (Int) -> Unit, highlightedIndex: Int? = null) {
    val baseColors = listOf(boxR, boxG, boxB, boxM, boxY, boxC)
    val colors = baseColors.mapIndexed { index, color ->
        if (index == highlightedIndex) color.copy(alpha = 0.5f) else color
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = matrixTopPadding_port),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(btnSpacing)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(btnSpacing)
        ) {
            Button(
                onClick = { onButtonClick(0) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors[0],
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                modifier = Modifier
                    .size(btnSize_port)
            ) {
                Text("")
            }

            Button(
                onClick = { onButtonClick(1) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors[1],
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                modifier = Modifier
                    .size(btnSize_port)
            ) {
                Text("")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(btnSpacing)
        ) {
            Button(
                onClick = { onButtonClick(2) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors[2],
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                modifier = Modifier
                    .size(btnSize_port)
            ) {
                Text("")
            }

            Button(
                onClick = { onButtonClick(3) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors[3],
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                modifier = Modifier
                    .size(btnSize_port)
            ) {
                Text("")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(btnSpacing)
        ) {
            Button(
                onClick = { onButtonClick(4) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors[4],
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                modifier = Modifier
                    .size(btnSize_port)
            ) {
                Text("")
            }

            Button(
                onClick = { onButtonClick(5) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors[5],
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                modifier = Modifier
                    .size(btnSize_port)
            ) {
                Text("")
            }
        }
    }
}

@Composable
fun ColorGrid_land(modifier: Modifier = Modifier, seqS : String, onButtonClick: (Int) -> Unit, highlightedIndex: Int? = null) {
    val baseColors = listOf(boxR, boxG, boxB, boxM, boxY, boxC)
    val colors = baseColors.mapIndexed { index, color ->
        if (index == highlightedIndex) color.copy(alpha = 0.5f) else color
    }

    Column(
        modifier = Modifier
            .padding(start = matrixLeftPadding_land, top = matrixTopPadding_land),
        verticalArrangement = Arrangement.spacedBy(btnSpacing)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(btnSpacing)
        ) {
            Button(
                onClick = { onButtonClick(0) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors[0],
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                modifier = Modifier
                    .size(btnSize_land)
            ) {
                Text("")
            }

            Button(
                onClick = { onButtonClick(1) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors[1],
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                modifier = Modifier
                    .size(btnSize_land)
            ) {
                Text("")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(btnSpacing)
        ) {
            Button(
                onClick = { onButtonClick(2) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors[2],
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                modifier = Modifier
                    .size(btnSize_land)
            ) {
                Text("")
            }

            Button(
                onClick = { onButtonClick(3) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors[3],
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                modifier = Modifier
                    .size(btnSize_land)
            ) {
                Text("")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(btnSpacing)
        ) {
            Button(
                onClick = { onButtonClick(4) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors[4],
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                modifier = Modifier
                    .size(btnSize_land)
            ) {
                Text("")
            }

            Button(
                onClick = { onButtonClick(5) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors[5],
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                modifier = Modifier
                    .size(btnSize_land)
            ) {
                Text("")
            }
        }
    }
}

@Composable
fun SequenceText_port(modifier: Modifier = Modifier, seqS : String, playState : Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = seqTextTopPadding_port)
            .alpha(if(playState) 1f else 0f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.seq))
        Text(
            text = seqS,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .wrapContentSize()
        )
    }
}

@Composable
fun SequenceText_land(modifier: Modifier = Modifier, seqS : String, playState : Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = seqTextTopPadding_land)
            .alpha(if(playState) 1f else 0f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.seq))
        Text(
            text = seqS,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun StartButton_port(modifier: Modifier = Modifier, playState : Boolean, startGame : () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
            Button(
                onClick = { startGame() },
                colors = ButtonDefaults.buttonColors(containerColor = startBtn),
                modifier = Modifier
                    .size(width =  actionButtonsWidth_port, height = actionButtonsHeight_port)
                    .alpha(if(!playState) 1f else 0f),
                enabled = !playState
            ) {
                Text(stringResource(R.string.startBtn))
            }
    }
}

@Composable
fun StartButton_land(modifier: Modifier = Modifier, playState : Boolean, startGame : () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp /*(state) { startButtonTopPadding_port } else { 0.dp }*/),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { startGame() },
            colors = ButtonDefaults.buttonColors(containerColor = startBtn),
            modifier = Modifier
                .size(width =  actionButtonsWidth_land, height = actionButtonsHeight_land)
                .alpha(if(!playState) 1f else 0f),
            enabled = !playState
        ) {
            Text(stringResource(R.string.startBtn))
        }
    }
}

@Composable
fun ActionButtons_port(modifier: Modifier = Modifier, seqS : String, pauseGame: () -> Unit, pauseState : Boolean, showingState : Boolean, playState : Boolean, navController: NavController, onAddGame: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = actionButtonsTopPadding_port),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { pauseGame() },
            colors = ButtonDefaults.buttonColors(containerColor = cancBtn),
            modifier = Modifier
                .size(width =  actionButtonsWidth_port, height = actionButtonsHeight_port)
                .alpha(if(showingState) 1f else 0f),
            enabled = showingState
        ) {
            Text(stringResource(if(pauseState) R.string.pauseBtn else R.string.resumeBtn))
        };

        Button(
            onClick = {
                onAddGame(seqS)
                navController.navigate("secondary")
            },
            colors = ButtonDefaults.buttonColors(containerColor = fineBtn),
            modifier = Modifier
                .size(width =  actionButtonsWidth_port, height = actionButtonsHeight_port)
                .alpha(if(playState) 1f else 0f),
            enabled = playState
        ) {
            Text(stringResource(R.string.finePartitaBtn))
        };
    }
}

@Composable
fun ActionButtons_land(modifier: Modifier = Modifier, seqS : String, pauseGame: () -> Unit, pauseState : Boolean, showingState : Boolean, playState : Boolean, navController: NavController,  onAddGame: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = actionButtonsUpperPadding_land),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {
                onAddGame(seqS)
                navController.navigate("secondary")
            },
            colors = ButtonDefaults.buttonColors(containerColor = fineBtn),
            modifier = Modifier
                .size(
                    width = actionButtonsWidth_land,
                    height = actionButtonsHeight_land
                )
                .alpha(if(playState) 1f else 0f),
            enabled = playState
        ) {
            Text(stringResource(R.string.finePartitaBtn))
        };
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = actionButtonsMiddlePadding_land),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { pauseGame() },
            colors = ButtonDefaults.buttonColors(containerColor = cancBtn),
            modifier = Modifier
                .size(
                    width = actionButtonsWidth_land,
                    height = actionButtonsHeight_land
                )
                .alpha(if(showingState) 1f else 0f),
            enabled = showingState
        ) {
            Text(stringResource(if(pauseState) R.string.pauseBtn else R.string.resumeBtn))
        }
    }
}

@Composable
fun GamesList_port(modifier: Modifier = Modifier, gamesList : List<String>) {
    LazyColumn (
        modifier = Modifier
            .size(width = listSizeX_port, height = listSizeY_port)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(8.dp))
    )
    {
        items(gamesList.size) { s ->
            GamesListItem(
                pressedCount = countSequence(gamesList[s]).toString(),
                pressedSeq = shortenSequence(s = gamesList[s])
            )
        }
    }
}

@Composable
fun GamesList_land(modifier: Modifier = Modifier, gamesList : List<String>) {
    LazyColumn (
        modifier = Modifier
            .size(width = listSizeX_port, height = listSizeY_port)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(8.dp))
    )
    {
        items(gamesList.size) { s ->
            GamesListItem(
                pressedCount = countSequence(gamesList[s]).toString(),
                pressedSeq = shortenSequence(s = gamesList[s])
            )
        }
    }
}

@Composable
fun GamesListItem(modifier: Modifier = Modifier, pressedCount : String, pressedSeq : String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text (
            modifier = Modifier
                .padding(listItemPadding),
            text = pressedCount,
            textAlign = TextAlign.Right
        )

        Text(
            modifier = Modifier
                .padding(listItemPadding)
                .clickable(onClick = {
                    //navController.navigate(pressedCount + "_" + pressedSeq)
                }),
            text = pressedSeq
        )
    }
}

@Composable
fun BackButton_port(modifier: Modifier = Modifier, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = backButtonTopPadding_port),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = backBtn),
            modifier = Modifier.size(
                width = backButtonWidth_port,
                height = backButtonHeight_port
            )
        ) {
            Text(stringResource(R.string.indietroBtn))
        }

    }
}

@Composable
fun BackButton_land(modifier: Modifier = Modifier, navController: NavController) {
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = backBtn),
            modifier = Modifier.size(
                width = backButtonWidth_land,
                height = backButtonHeight_land
            )
        ) {
            Text(stringResource(R.string.indietroBtn))
        }
}

@Composable
fun LanguageIcon(modifier : Modifier = Modifier) {
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

//------------------------------------------------------------



//------------------------------------------------------------

fun appendColorToSequence(index: Int, s: String): String {
    val newColor = when(index) {
        0 -> "R"
        1 -> "G"
        2 -> "B"
        3 -> "M"
        4 -> "Y"
        5 -> "C"
        else -> ""
    }

    return if (s.isEmpty()) newColor else "$s, $newColor"
}

fun getIndexFromColor(c : Char) : Int {
    val index = when(c) {
        'R' -> 0
        'G' -> 1
        'B' -> 2
        'M' -> 3
        'Y' -> 4
        'C' -> 5
        else -> -1
    }

    return index
}

fun shortenSequence(maxChar : Int = 5, s: String) : String {
    //3*maxChar-2 corresponds to the number of characters in the string for maxChar colors pressed
    val charCount = 3 * maxChar - 2

    if(s.isEmpty()) {
        return "..."
    }

    if(s.length > (charCount)) {
        return (s.substring(startIndex =  0, endIndex =  charCount) + "...")
    } else {
        return s
    }
}

fun countSequence(s : String) : Int {
    return s.length - (s.count{it == ' '} + s.count{it == ','})
}

fun addToRandomSequence(s : String) : String {
    val r = Random.nextInt(0, 6)
    return appendColorToSequence(r, s)
}

fun setLanguage(context: Context, languageCode: String) {
    val locale = java.util.Locale(languageCode)
    java.util.Locale.setDefault(locale)
    val config = context.resources.configuration
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}

fun playAudio(index : Int) {
    if(index in 0 .. 6) {

    }
}