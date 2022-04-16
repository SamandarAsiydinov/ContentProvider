package com.samsdk.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.samsdk.contentprovider.database.MyHelper
import com.samsdk.contentprovider.util.Constants.TABLE_NAME

class AcronymProvider : ContentProvider() {

    companion object {
        private const val PROVIDER_NAME = "com.samsdk.contentprovider/AcronymProvider"
        val URL = "content://$PROVIDER_NAME/$TABLE_NAME"
        val CONTENT_URI = Uri.parse(URL)

        val _ID = "_id"
        val NAME = "NAME"
        val MEANING = "MEANING"
    }

    private var db: SQLiteDatabase? = null

    override fun onCreate(): Boolean {
        val helper = MyHelper(context)
        db = helper.writableDatabase
        return db != null
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        db?.insert(TABLE_NAME, null, cv)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun update(
        uri: Uri,
        cv: ContentValues?,
        condition: String?,
        condition_val: Array<out String>?
    ): Int {
        val count = db?.update(TABLE_NAME, cv, condition, condition_val)
        context?.contentResolver?.notifyChange(uri, null)
        return count!!
    }

    override fun delete(uri: Uri, p1: String?, p2: Array<out String>?): Int {
        val count = db?.delete(TABLE_NAME, p1, p2)
        context?.contentResolver?.notifyChange(uri, null)
        return count!!
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        con_val: Array<out String>?,
        order: String?
    ): Cursor? {
        return db?.query(TABLE_NAME, cols, condition, con_val, null, null, order)
    }

    override fun getType(p0: Uri): String? {
        return "vnd.android.cursor.dir/vnd.example.my_table"
    }
}