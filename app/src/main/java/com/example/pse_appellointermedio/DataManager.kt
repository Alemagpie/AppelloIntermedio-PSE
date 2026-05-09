package com.example.pse_appellointermedio

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log

//Wrapper for a SQLite db
class DataManager(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    //Constants
    companion object {
        private const val DB_NAME = "simonAppDb.db"
        private const val DB_VERSION = 2
        const val TABLE_NAME = "games"
        const val INPUT_COUNT = "input_count"
        const val SEQUENCE = "sequence"

        const val SQL_CREATE_TABLE = "create table " + TABLE_NAME + " (" + BaseColumns._ID +
                " integer primary key autoincrement, " + INPUT_COUNT + " int not null, " +
                SEQUENCE + " text not null);"

        const val SQL_SELECT_GAMES = "SELECT $INPUT_COUNT, $SEQUENCE FROM $TABLE_NAME"
        const val SQL_SELECT_LATEST_GAME = "SELECT $INPUT_COUNT, $SEQUENCE FROM $TABLE_NAME ORDER BY ${BaseColumns._ID} DESC LIMIT 1"
    }


    override fun onCreate(db: SQLiteDatabase)
    {
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {
        //No upgrades
    }

    //Adds index at which the error was made and the sequence to the db
    fun addGame(errorIndex : Int, sequence : String){
        //Param checks
        if(errorIndex < 0 || sequence.isEmpty()) {
            Log.e("Android:runtime", "Illegal parameters passed to database")
            return
        }

        val values = ContentValues()
        values.put(INPUT_COUNT, errorIndex)
        values.put(SEQUENCE, sequence)

        writableDatabase.insert(TABLE_NAME, null, values)
    }

    //Returns a list of GameRecords
    fun getGames() : List<GameRecord> {
        val games = mutableListOf<GameRecord>()
        //Iterator to result of the query
        val cursor = readableDatabase.rawQuery(
            SQL_SELECT_GAMES,
            null
        )

        //Get all records
        while(cursor.moveToNext()) {
            games.add(
                GameRecord(
                    errorIndex = cursor.getInt(cursor.getColumnIndexOrThrow(INPUT_COUNT)),
                    sequence = cursor.getString(cursor.getColumnIndexOrThrow(SEQUENCE))
                )
            )
        }

        cursor.close()
        return games
    }

    //Gets the latest inserted game by ordering the records by descending id and selecting only the first one
    //? is needed since the returned game can be null
    fun getLatestGame() : GameRecord? {
        val cursor = readableDatabase.rawQuery(
            SQL_SELECT_LATEST_GAME,
            null
        )

        //Get first record if exists, otherwise null
        val game = if(cursor.moveToFirst()) {
            GameRecord(
                errorIndex = cursor.getInt(cursor.getColumnIndexOrThrow(INPUT_COUNT)),
                sequence = cursor.getString(cursor.getColumnIndexOrThrow(SEQUENCE))
            )
        } else null

        cursor.close()
        return game
    }
}