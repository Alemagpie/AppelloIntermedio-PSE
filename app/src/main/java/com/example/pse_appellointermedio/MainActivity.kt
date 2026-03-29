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
                    ColorGrid(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun ColorGrid(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = boxR),
                modifier = Modifier.size(width =  80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            };

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = boxG),
                modifier = Modifier.size(width =  80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = boxB),
                modifier = Modifier.size(width =  80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            };

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = boxM),
                modifier = Modifier.size(width =  80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = boxY),
                modifier = Modifier.size(width =  80.dp, height = 80.dp)
            ) {
                Text(stringResource(R.string.emptyChar))
            };

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = boxC),
                modifier = Modifier.size(width =  80.dp, height = 80.dp)
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
            Text(stringResource(R.string.fine-partitaBtn))
        };
    }
}