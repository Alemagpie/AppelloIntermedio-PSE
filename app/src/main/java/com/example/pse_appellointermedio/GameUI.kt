package com.example.pse_appellointermedio

import android.content.res.Configuration
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pse_appellointermedio.ui.theme.boxB
import com.example.pse_appellointermedio.ui.theme.boxC
import com.example.pse_appellointermedio.ui.theme.boxG
import com.example.pse_appellointermedio.ui.theme.boxM
import com.example.pse_appellointermedio.ui.theme.boxR
import com.example.pse_appellointermedio.ui.theme.boxY
import com.example.pse_appellointermedio.ui.theme.cancBtn
import com.example.pse_appellointermedio.ui.theme.errorColor
import com.example.pse_appellointermedio.ui.theme.fineBtn
import com.example.pse_appellointermedio.ui.theme.startBtn
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainUI(modifier: Modifier = Modifier, navController: NavController, viewModel: GameViewModel) {

    fun addGame(i: Int, s : String) {
        //If it's showing the first sequence, don't add the entry
        if(!(viewModel.isShowingSequence && viewModel.sequenceLength <= 1)) {
            viewModel.addGame(i, s)
        }
    }

    //Handles both kinds of input: system button and gesture
    BackHandler() {
        //Back can be called after an error or while inputting sequence, so the two cases must be distinguished
        val length = if(viewModel.errorState) viewModel.inputLength - 1 else viewModel.inputLength
        addGame(length, viewModel.proposedSequence)
        navController.popBackStack()
    }

    //Sound stuff
    val context = LocalContext.current
    val soundPlayer = remember{ SoundPlayer(context) }
    val playColorAudio : (Int) -> Unit = {index ->
        if(index in 0 .. 6) {
            soundPlayer.playSound(index)
        }
    }

    //Two UIs for both configurations
    val configuration = LocalConfiguration.current
    if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        ErrorPanel(errorState= viewModel.errorState)

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
                    viewModel.sequenceString,
                    onButtonClick = { index ->
                        viewModel.addAndCheckColor(index, playColorAudio)
                        if(viewModel.hasStartedGame && !viewModel.isShowingSequence) playColorAudio(index)
                    },
                    viewModel.hIndex
                )
            }

            Column() {
                SequenceText_land(
                    Modifier,
                    viewModel.sequenceString,
                    viewModel.hasStartedGame
                )

                StartButton_land(
                    Modifier,
                    viewModel.hasStartedGame,
                    startGame = {
                        viewModel.hasStartedGame = true
                        viewModel.startSequence(playColorAudio)
                    }
                )

                ActionButtons_land(
                    Modifier,
                    viewModel.sequenceString,
                    pauseGame = { viewModel.isPaused = !viewModel.isPaused },
                    viewModel.errorState,
                    viewModel.isPaused,
                    viewModel.isShowingSequence,
                    viewModel.hasStartedGame,
                    navController,
                    onAddGame = { addGame(viewModel.inputLength, viewModel.proposedSequence) }
                )
            }
        }
    } else {
        ErrorPanel(errorState= viewModel.errorState)

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
                viewModel.sequenceString,
                onButtonClick = { index ->
                    viewModel.addAndCheckColor(index, playColorAudio)
                    if(viewModel.hasStartedGame && !viewModel.isShowingSequence) playColorAudio(index)
                },
                viewModel.hIndex
            )

            SequenceText_port(
                Modifier,
                viewModel.sequenceString,
                viewModel.hasStartedGame
            )

            StartButton_port(
                Modifier,
                viewModel.hasStartedGame,
                startGame = {
                    viewModel.hasStartedGame = true
                    viewModel.startSequence(playColorAudio)
                }
            )

            ActionButtons_port(
                Modifier,
                viewModel.sequenceString,
                pauseGame = { viewModel.isPaused = !viewModel.isPaused },
                viewModel.errorState,
                viewModel.isPaused,
                viewModel.isShowingSequence,
                viewModel.hasStartedGame,
                navController,
                onAddGame = { addGame(viewModel.inputLength, viewModel.proposedSequence) }
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
                .height(seqTextHeight_port),
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
            maxLines = 10,
            //overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .wrapContentSize()
                .height(seqTextHeight_land),
            textAlign = TextAlign.Center
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
fun ActionButtons_port(modifier: Modifier = Modifier, seqS : String, pauseGame: () -> Unit, errorState : Boolean, pauseState : Boolean, showingState : Boolean, playState : Boolean, navController: NavController, onAddGame: () -> Unit) {
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
            Text(stringResource(if(pauseState) R.string.resumeBtn else R.string.pauseBtn))
        };

        Button(
            onClick = {
                onAddGame()
                navController.navigate("gamesList")
            },
            colors = ButtonDefaults.buttonColors(containerColor = fineBtn),
            modifier = Modifier
                .size(width =  actionButtonsWidth_port, height = actionButtonsHeight_port)
                .alpha(if(playState && !errorState) 1f else 0f),
            enabled = playState
        ) {
            Text(stringResource(R.string.finePartitaBtn))
        };
    }
}

@Composable
fun ActionButtons_land(modifier: Modifier = Modifier, seqS : String, pauseGame: () -> Unit, errorState : Boolean, pauseState : Boolean, showingState : Boolean, playState : Boolean, navController: NavController,  onAddGame: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = actionButtonsUpperPadding_land),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {
                onAddGame()
                navController.navigate("gamesList")
            },
            colors = ButtonDefaults.buttonColors(containerColor = fineBtn),
            modifier = Modifier
                .size(
                    width = actionButtonsWidth_land,
                    height = actionButtonsHeight_land
                )
                .alpha(if(playState && !errorState) 1f else 0f),
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
            Text(stringResource(if(pauseState) R.string.resumeBtn else R.string.pauseBtn))
        }
    }
}

@Composable
fun ErrorPanel(modifier : Modifier = Modifier, errorState : Boolean) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .alpha(if(errorState) 1f else 0f)
            .background(errorColor)
    ) {

    }
}