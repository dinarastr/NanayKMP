package com.dinarastepina.nanaykmp.data.driver

import android.content.Context
import androidx.room.Room
import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.AndroidSQLiteDriver
import com.dinarastepina.nanaykmp.data.DictionaryDataBase

actual class DatabaseDriver(private val context: Context) {
    actual fun createDriver(): SQLiteDriver {
        val db = Room.databaseBuilder(
            context,
            DictionaryDataBase::class.java,
            "nanay_to_russian.db"
        )
            .fallbackToDestructiveMigration(true)
            .createFromAsset("files/nanay_to_russian")
            .build()
            
        val writableDb = db.openHelper.writableDatabase
        writableDb.close()
        
        return AndroidSQLiteDriver()
    }
} 