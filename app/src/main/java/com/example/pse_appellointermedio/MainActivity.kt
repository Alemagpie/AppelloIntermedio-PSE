package com.example.pse_appellointermedio

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.res.colorResource
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
import androidx.activity.addCallback
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.text.style.TextAlign
import com.example.pse_appellointermedio.ui.theme.backBtn
import com.example.pse_appellointermedio.ui.theme.listOutline


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
val actionButtonsWidth_port = 160.dp
val actionButtonsWidth_land = 250.dp
val actionButtonsHeight_port = 80.dp
val actionButtonsHeight_land = 50.dp
val actionButtonsTopPadding_port = 100.dp
val actionButtonsUpperPadding_land = 50.dp
val actionButtonsMiddlePadding_land = 20.dp
val backButtonTopPadding_port = 30.dp
val backButtonTopPadding_land = 50.dp
val backButtonWidth_port = 160.dp
val backButtonWidth_land = 250.dp
val backButtonHeight_port = 80.dp
val backButtonHeight_land = 50.dp
val listTopPadding_port = 80.dp
val listSizeX_port = 200.dp
val listSizeY_port = 300.dp
val listItemPadding = 15.dp


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
fun MainUI(modifier: Modifier = Modifier, navController: NavController) {
    var sequenceString by rememberSaveable{ mutableStateOf("") }
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
                    onButtonClick = { index -> sequenceString = appendColorToSequence(index, sequenceString) }
                )
            }

            Column() {
                SequenceText_land(
                    Modifier,
                    sequenceString
                )

                ActionButtons_land(
                    Modifier,
                    sequenceString,
                    deleteSequence = { sequenceString = "" },
                    navController
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
                onButtonClick = { index -> sequenceString = appendColorToSequence(index, sequenceString) }
            )

            SequenceText_port(
                Modifier,
                sequenceString
            )

            ActionButtons_port(
                Modifier,
                sequenceString,
                deleteSequence = { sequenceString = "" },
                navController
            )
        }
    }
}

@Composable
fun SecondaryUI(modifier: Modifier = Modifier, navController: NavController) {
    BackHandler() {
        navController.popBackStack()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = listTopPadding_port),
        horizontalArrangement = Arrangement.Center
    ) {
        LazyColumn (
            modifier = Modifier
                .size(width = listSizeX_port, height = listSizeY_port)
                .border(width = 1.dp, color = listOutline, shape = RoundedCornerShape(8.dp))
            )
        {
            items(30) { index ->
                GamesListItem(Modifier, "$index", "aaa")
            }
        }
    }


    val configuration = LocalConfiguration.current

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BackButton_port(Modifier, navController)
        }

    } else {
        BackButton_land(Modifier, navController)
    }

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
fun ColorGrid_port(modifier: Modifier = Modifier, seqS : String, onButtonClick: (Int) -> Unit) {
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
                    containerColor = boxR,
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
                    containerColor = boxG,
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
                    containerColor = boxB,
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
                    containerColor = boxM,
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
                    containerColor = boxY,
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
                    containerColor = boxC,
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
fun ColorGrid_land(modifier: Modifier = Modifier, seqS : String, onButtonClick: (Int) -> Unit) {
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
                    containerColor = boxR,
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
                    containerColor = boxG,
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
                    containerColor = boxB,
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
                    containerColor = boxM,
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
                    containerColor = boxY,
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
                    containerColor = boxC,
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
fun SequenceText_port(modifier: Modifier = Modifier, seqS : String) {
    Row(
        modifier = Modifier
        .fillMaxWidth()
        .padding(top = seqTextTopPadding_port),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.seq))
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = seqS,
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Composable
fun SequenceText_land(modifier: Modifier = Modifier, seqS : String) {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = seqTextTopPadding_land),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(stringResource(R.string.seq))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = seqS,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}

@Composable
fun ActionButtons_port(modifier: Modifier = Modifier, seqS : String, deleteSequence: () -> Unit, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = actionButtonsTopPadding_port),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { deleteSequence() },
            colors = ButtonDefaults.buttonColors(containerColor = cancBtn),
            modifier = Modifier.size(width =  actionButtonsWidth_port, height = actionButtonsHeight_port)
        ) {
            Text(stringResource(R.string.cancellaBtn))
        };

        Button(
            onClick = { navController.navigate("secondary") },
            colors = ButtonDefaults.buttonColors(containerColor = fineBtn),
            modifier = Modifier.size(width =  actionButtonsWidth_port, height = actionButtonsHeight_port)
        ) {
            Text(stringResource(R.string.finePartitaBtn))
        };
    }
}

@Composable
fun ActionButtons_land(modifier: Modifier = Modifier, seqS : String, deleteSequence: () -> Unit, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = actionButtonsUpperPadding_land),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { navController.navigate("secondary") },
            colors = ButtonDefaults.buttonColors(containerColor = fineBtn),
            modifier = Modifier.size(
                width = actionButtonsWidth_land,
                height = actionButtonsHeight_land
            )
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
            onClick = { deleteSequence() },
            colors = ButtonDefaults.buttonColors(containerColor = cancBtn),
            modifier = Modifier.size(
                width = actionButtonsWidth_land,
                height = actionButtonsHeight_land
            )
        ) {
            Text(stringResource(R.string.cancellaBtn))
        }
    }
}

@Composable
fun GamesList_port(modifier: Modifier = Modifier) {

}

@Composable
fun GamesList_land(modifier: Modifier = Modifier) {

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
                .padding(listItemPadding),
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = backButtonTopPadding_land),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
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
}
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