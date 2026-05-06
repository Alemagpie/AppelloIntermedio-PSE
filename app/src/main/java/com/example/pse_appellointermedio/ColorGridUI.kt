package com.example.pse_appellointermedio

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pse_appellointermedio.ui.theme.boxB
import com.example.pse_appellointermedio.ui.theme.boxC
import com.example.pse_appellointermedio.ui.theme.boxG
import com.example.pse_appellointermedio.ui.theme.boxM
import com.example.pse_appellointermedio.ui.theme.boxR
import com.example.pse_appellointermedio.ui.theme.boxY
import com.example.pse_appellointermedio.ui.theme.cancBtn
import com.example.pse_appellointermedio.ui.theme.fineBtn
import com.example.pse_appellointermedio.ui.theme.startBtn
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainUI(modifier: Modifier = Modifier, navController: NavController, gamesList : List<String>, onAddGame: (String) -> Unit) {
    //User inputted sequence
    var sequenceString by rememberSaveable{ mutableStateOf("") }
    //Computer generated sequence
    var proposedSequence by remember { mutableStateOf("") }
    //Computer sequence lenght
    var sequenceLength by remember{ mutableIntStateOf(0)}
    //User input length
    var inputLength by remember{ mutableIntStateOf(0)}

    //State variables
    var isPaused by rememberSaveable{ mutableStateOf(false) }
    var isShowingSequence by rememberSaveable{ mutableStateOf(false) }
    var hasStartedGame by rememberSaveable{ mutableStateOf(false) }

    //Sound stuff
    val context = LocalContext.current
    val soundPlayer = remember{ SoundPlayer(context) }
    val playColorAudio : (Int) -> Unit = {index ->
        if(index in 0 .. 6) {
            soundPlayer.playSound(index)
        }
    }

    //Sequence generation coroutine
    val scope = rememberCoroutineScope()
    var job by remember{ mutableStateOf<Job?>(null) }
    var hIndex by remember{ mutableStateOf<Int?>(null) }
    //Generates increasingly longer sequences
    val startSequence : () -> Unit = {  //atm it stops if screen is rotated
        job = scope.launch {
            delay(500)
            //Reset user input (delay serves to prevent it from being deleted as soon as it's inputted)
            inputLength = 0
            sequenceString = ""
            delay(200)

            //isShowingSequence acts as a mutex for other logic
            isShowingSequence = true
            proposedSequence = addToRandomSequence(proposedSequence)
            val s = proposedSequence.replace(", ", "")
            sequenceLength++

            for (c in s) {
                //Set variable to highlight the color chosen by the computer
                hIndex = getIndexFromColor(c)
                playColorAudio(hIndex as Int)
                delay(600)
                hIndex = null
                delay(200)
            }
            isShowingSequence = false
        }
    }

    //Adds color to user's sequence and checks for errors
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
                //Log.i("seq", "mismatch")

            }
        }
    }

    //Two UIs for both configurations
    val configuration = LocalConfiguration.current
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
                    if(!isShowingSequence) playColorAudio(index)
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
            maxLines = 10,
            //overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .wrapContentSize()
                .height(80.dp),
            textAlign = TextAlign.Center
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
