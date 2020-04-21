package com.lk.firstkotlin.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.lk.firstkotlin.model.UserModel

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser(user: UserModel): Boolean {
// Gets the data repository in write mode
        val db = writableDatabase
// Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(USER_NAME, user.name)


// Insert the new row, returning the primary key value of the new row
        db.insert(TABLE_NAME, null, values)
        db.close()
        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteUser(userid: String): Boolean {
// Gets the data repository in write mode
        val db = writableDatabase
// Define 'where' part of query.
        val selection = USER_ID + " LIKE ?"
// Specify arguments in placeholder order.
        val selectionArgs = arrayOf(userid)
// Issue SQL statement.
        db.delete(TABLE_NAME, selection, selectionArgs)
        return true
    }



    fun getAllUser(): ArrayList<String> {

        // array of columns to fetch
        val columns = arrayOf(USER_NAME)

        // sorting orders
        val sortOrder = "$USER_NAME ASC"
        val userList = ArrayList<String>()

        val db = this.readableDatabase

        // query the user table
        val cursor = db.query(
            TABLE_NAME, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {
                userList.add( cursor.getString(cursor.getColumnIndex(USER_NAME))

                )


            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "User.db"
        val TABLE_NAME = "USER";
        val USER_ID = "USER_ID";
        val USER_NAME = "NAME";


        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    USER_NAME + " TEXT NOT NULL)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME
    }
}