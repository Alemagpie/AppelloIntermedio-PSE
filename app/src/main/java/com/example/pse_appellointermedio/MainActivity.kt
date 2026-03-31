package com.example.pse_appellointermedio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pse_appellointermedio.ui.theme.PSEAppelloIntermedioTheme
import com.example.pse_appellointermedio.ui.theme.boxR
import com.example.pse_appellointermedio.ui.theme.boxG
import com.example.pse_appellointermedio.ui.theme.boxB
import com.example.pse_appellointermedio.ui.theme.boxM
import com.example.pse_appellointermedio.ui.theme.boxY
import com.example.pse_appellointermedio.ui.theme.boxC


val mainTopPadding = 50.dp
val spacerHeight = 20.dp
val btnSize = 100.dp
val btnSpacing = 10.dp
val btnRadius = 8.dp
val matrixTopPadding = 60.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PSEAppelloIntermedioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainUI(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun MainUI(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = mainTopPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Title()

        Spacer(modifier = Modifier.height(spacerHeight))

        ColorGrid()

        //Spacer(modifier = Modifier.height(20.dp))

        SequenceText()
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text("PSE - Appello intermedio")
    }
}

@Composable
fun ColorGrid(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = matrixTopPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(btnSpacing)
    ) {
        Row(
            // Use the passed-in modifier for the Row
            //modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(btnSpacing)
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = boxR,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                // Use Uppercase 'Modifier' here so it doesn't inherit 'fillMaxWidth'
                modifier = Modifier
                    .size(btnSize)
                   // .padding( 8.dp)
            ) {
                Text("")
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = boxG,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                // Use Uppercase 'Modifier' here so it doesn't inherit 'fillMaxWidth'
                modifier = Modifier
                    .size(btnSize)
                   // .padding(8.dp)
            ) {
                Text("")
            }
        }

        Row(
            // Use the passed-in modifier for the Row
            //modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(btnSpacing)
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = boxB,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                // Use Uppercase 'Modifier' here so it doesn't inherit 'fillMaxWidth'
                modifier = Modifier
                    .size(btnSize)
                   // .padding(8.dp)
            ) {
                Text("")
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = boxM,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                // Use Uppercase 'Modifier' here so it doesn't inherit 'fillMaxWidth'
                modifier = Modifier
                    .size(btnSize)
                   // .padding(8.dp)
            ) {
                Text("")
            }
        }

        Row(
            // Use the passed-in modifier for the Row
            //modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(btnSpacing)
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = boxY,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                // Use Uppercase 'Modifier' here so it doesn't inherit 'fillMaxWidth'
                modifier = Modifier
                    .size(btnSize)
                   // .padding(8.dp)
            ) {
                Text("")
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = boxC,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(btnRadius),
                // Use Uppercase 'Modifier' here so it doesn't inherit 'fillMaxWidth'
                modifier = Modifier
                    .size(btnSize)
                  //  .padding(8.dp)
            ) {
                Text("")
            }
        }
    }
}


@Composable
fun SequenceText(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text("This is the sequence")
    }
}

@Composable
fun BottomButtons(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = boxY),
            modifier = Modifier.size(width =  50.dp, height = 100.dp)
        ) {
            Text(stringResource(R.string.cancellaBtn))
        };

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = boxY),
            modifier = Modifier.size(width =  50.dp, height = 100.dp)
        ) {
            Text(stringResource(R.string.finePartitaBtn))
        };
    }
}