package com.dinarastepina.nanaykmp.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import nanaykmp.composeapp.generated.resources.Res
import java.io.IOException

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<DictionaryDataBase> {
    val appContext = ctx.applicationContext
    val dbFileName = "nanay_dictionary.db" // The name you want for the database file on the device
    val dbFile = appContext.getDatabasePath(dbFileName)

    runBlocking {
        copyPrepopulatedDatabaseIfNecessary(appContext)
    }
    return Room.databaseBuilder<DictionaryDataBase>(
        context = appContext,
        name = dbFile.absolutePath // Pass the absolute path of the database file
    )
}

suspend fun copyPrepopulatedDatabaseIfNecessary(context: Context) {
    val dbFileName = "nanay_dictionary.db"
    val dbFile = context.getDatabasePath(dbFileName)

    // Only copy if the file doesn't already exist
    if (!dbFile.exists()) {
        // Ensure the parent directories exist
        dbFile.parentFile?.mkdirs()

        try {
            // Read the bytes from the Compose shared resource
            val dbBytes = withContext(Dispatchers.IO) {
                // Replace 'talysh_to_russian' with the actual identifier for your .db file resource
                Res.readBytes("files/talysh_to_russian")
            }
            withContext(Dispatchers.IO) {
                dbFile.writeBytes(dbBytes)
                println("Successfully copied prepopulated database to: ${dbFile.absolutePath}")
            }

        } catch (e: IOException) {
            e.printStackTrace()
            println("Error copying prepopulated database: ${e.message}")
            // Handle the error appropriately (e.g., show an error message, retry)
        } catch (e: Exception) {
            e.printStackTrace()
            println("An unexpected error occurred: ${e.message}")
            // Handle the error appropriately (e.g., show an error message, retry)
        }
    }
}

