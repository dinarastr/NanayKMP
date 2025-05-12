package com.dinarastepina.nanaykmp.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import nanaykmp.composeapp.generated.resources.Res
import java.io.File
import java.io.IOException

private const val DATABASE_NAME = "nanay_dictionary.db"
private const val PREPOPULATED_DATABASE_FILE = "files/talysh_to_russian"

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<DictionaryDataBase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath(DATABASE_NAME)

    if (!dbFile.exists()) {
        runBlocking {
            dbFile.copyPrepopulatedDatabase()
        }
    }

    return Room.databaseBuilder<DictionaryDataBase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}

private suspend fun File.copyPrepopulatedDatabase() {
    parentFile?.mkdirs()
    try {
        val dbBytes = withContext(Dispatchers.IO) {
            Res.readBytes(PREPOPULATED_DATABASE_FILE)
        }
        withContext(Dispatchers.IO) {
            writeBytes(dbBytes)
            println("Successfully copied prepopulated database to: ${absolutePath}")
        }

    } catch (e: IOException) {
        e.printStackTrace()
        println("Error copying prepopulated database: ${e.message}")
    } catch (e: Exception) {
        e.printStackTrace()
        println("An unexpected error occurred: ${e.message}")
    }
}