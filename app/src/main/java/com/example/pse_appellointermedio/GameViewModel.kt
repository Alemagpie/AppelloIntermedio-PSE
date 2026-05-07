package com.example.pse_appellointermedio

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

//ViewModel used to hold sequences, states and coroutines
//Used to make coroutines survive recomposition
class GameViewModel() : ViewModel() {
    var sequenceString by mutableStateOf("")
    var proposedSequence by mutableStateOf("")
    var sequenceLength by mutableIntStateOf(0)
    var inputLength by mutableIntStateOf(0)

    var isPaused by mutableStateOf(false)
    var isShowingSequence by mutableStateOf(false)
    var hasStartedGame by mutableStateOf(false)
    var hIndex by mutableStateOf<Int?>(null)
    var errorState by mutableStateOf(false)

    fun startSequence(playColorAudio : (Int) -> Unit) {
        viewModelScope.launch {
            delay(500)
            inputLength = 0
            sequenceString = ""
            delay(200)

            isShowingSequence = true
            proposedSequence = addToRandomSequence(proposedSequence)
            val s = proposedSequence.replace(", ", "")
            sequenceLength++

            for (c in s) {
                waitIfPaused()
                hIndex = getIndexFromColor(c)
                playColorAudio(hIndex as Int)
                delay(600)
                hIndex = null
                delay(200)
            }
            isShowingSequence = false
        }
    }

    private suspend fun waitIfPaused() {
        while (isPaused) {
            delay(100)
        }
    }

    //Adds color to user's sequence and checks for errors
    val addAndCheckColor : (Int, (Int) -> Unit) -> Unit = {i, playColorAudio ->
        if(!isShowingSequence) {
            sequenceString = appendColorToSequence(i, sequenceString)
            inputLength++

            if(sequenceString == proposedSequence.subSequence(0, sequenceString.length).toString()) {
                if(inputLength == sequenceLength)
                    startSequence(playColorAudio)
                //Log.i("seq", "match")
            } else {
                //Error screen
                //Log.i("seq", "mismatch")
                errorState = true
            }
        }
    }
}