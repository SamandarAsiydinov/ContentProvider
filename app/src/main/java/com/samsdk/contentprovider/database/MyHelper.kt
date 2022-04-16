package com.samsdk.contentprovider.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context?)
    : SQLiteOpenHelper(context, "SQLite", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE MY_TABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, MEANING TEXT)")
        db?.execSQL("INSERT INTO MY_TABLE(NAME, MEANING) VALUES('Samandar','Android Developer')")
        db?.execSQL("INSERT INTO MY_TABLE(NAME, MEANING) VALUES('Nurmuhammad','Android Developer')")
        db?.execSQL("INSERT INTO MY_TABLE(NAME, MEANING) VALUES('Ogabek','Android Developer')")
        db?.execSQL("INSERT INTO MY_TABLE(NAME, MEANING) VALUES('Bogibek','Android Developer')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}