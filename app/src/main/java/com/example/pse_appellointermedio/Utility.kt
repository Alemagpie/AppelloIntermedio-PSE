package com.example.pse_appellointermedio

import android.content.Context
import kotlin.random.Random

//Takes in a string and a color index, returns string with new color added
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

//Returns color index from color initial
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

//Checks if sequence is too long. if it is, it gets shortened and "..." are added at the end
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

//Returns number of colors in a sequence
fun countSequence(s : String) : Int {
    return s.length - (s.count{it == ' '} + s.count{it == ','})
}

//Adds a random color initial to the provided string
fun addToRandomSequence(s : String) : String {
    val r = Random.nextInt(0, 6)
    return appendColorToSequence(r, s)
}

//Changes language based on provided language code
fun setLanguage(context: Context, languageCode: String) {
    val locale = java.util.Locale(languageCode)
    java.util.Locale.setDefault(locale)
    val config = context.resources.configuration
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}