package com.dinarastepina.nanaykmp.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dinarastepina.nanaykmp.data.driver.DatabaseDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nanaykmp.composeapp.generated.resources.Res
import java.io.File
import java.io.IOException

private const val DATABASE_NAME = "nanay_to_russian.db"
private const val PREPOPULATED_DATABASE_FILE = "files/nanay_to_russian"
private const val CURRENT_SCHEMA_VERSION = 3 // Update this when you change the schema

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<DictionaryDataBase> {
    val appContext = ctx.applicationContext
    return Room.databaseBuilder<DictionaryDataBase>(
        context = appContext,
        name = "nanay_to_russian.db"
    ).setDriver(DatabaseDriver(appContext).createDriver())
}

private suspend fun File.copyPrepopulatedDatabase() {
    parentFile?.mkdirs()
    try {
        val dbBytes = withContext(Dispatchers.IO) {
            Res.readBytes(PREPOPULATED_DATABASE_FILE)
        }
        withContext(Dispatchers.IO) {
            writeBytes(dbBytes)
            println("Successfully copied prepopulated database to: $absolutePath")
        }

    } catch (e: IOException) {
        e.printStackTrace()
        println("Error copying prepopulated database: ${e.message}")
    } catch (e: Exception) {
        e.printStackTrace()
        println("An unexpected error occurred: ${e.message}")
    }
}