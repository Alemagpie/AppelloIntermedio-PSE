package com.example.pse_appellointermedio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PSEAppelloIntermedioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    aaa(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun aaa(modifier: Modifier = Modifier) {
    Column {
        Row(
            // Use the passed-in modifier for the Row
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = boxR,
                    contentColor = Color.Black
                ),
                // Use Uppercase 'Modifier' here so it doesn't inherit 'fillMaxWidth'
                modifier = Modifier.size(width = 80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = boxG,
                    contentColor = Color.Black
                ),
                // Use Uppercase 'Modifier' here as well
                modifier = Modifier.size(width = 80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            }
        }

        Row(
            // Use the passed-in modifier for the Row
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = boxB,
                    contentColor = Color.Black
                ),
                // Use Uppercase 'Modifier' here so it doesn't inherit 'fillMaxWidth'
                modifier = Modifier.size(width = 80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = boxM,
                    contentColor = Color.Black
                ),
                // Use Uppercase 'Modifier' here as well
                modifier = Modifier.size(width = 80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            }
        }

        Row(
            // Use the passed-in modifier for the Row
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = boxY,
                    contentColor = Color.Black
                ),
                // Use Uppercase 'Modifier' here so it doesn't inherit 'fillMaxWidth'
                modifier = Modifier.size(width = 80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = boxC,
                    contentColor = Color.Black
                ),
                // Use Uppercase 'Modifier' here as well
                modifier = Modifier.size(width = 80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            }
        }
    }
}

@Composable
fun ColorGrid(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = boxR),
                modifier = modifier.size(width =  80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            };

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = boxG),
                modifier = modifier.size(width =  80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            }
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = boxB),
                modifier = modifier.size(width =  80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            };

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = boxM),
                modifier = modifier.size(width =  80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            }
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = boxY),
                modifier = modifier.size(width =  80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            };

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = boxC),
                modifier = modifier.size(width =  80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            }
        }
    }
}

@Composable
fun SequenceText(modifier: Modifier = Modifier) {

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