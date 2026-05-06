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
val seqTextTopPadding_port = 20.dp
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