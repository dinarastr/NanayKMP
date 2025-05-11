package com.dinarastepina.nanaykmp.data

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.runBlocking
import nanaykmp.composeapp.generated.resources.Res
import platform.Foundation.NSData
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask
import platform.Foundation.dataWithBytes

@OptIn(ExperimentalForeignApi::class) // Needed for NSFileManager
 fun getDatabaseBuilder(): RoomDatabase.Builder<DictionaryDataBase> { // iOS often doesn't need Context directly here
    val fileManager = NSFileManager.defaultManager
    val documentsDirectory = fileManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = true, // Create directory if it doesn't exist
        error = null,
    )
    val dbFileName = "nanay_dictionary.db" // Your desired database file name
    val dbFilePath = documentsDirectory?.path + "/$dbFileName"
// Check if the database file already exists
    val dbFileExists = fileManager.fileExistsAtPath(dbFilePath)

    if (!dbFileExists) {
        // Database does not exist, copy from shared resources
        val dbBytes = runBlocking {
            try {
                Res.readBytes("files/talysh_to_russian")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
        if (dbBytes != null) {
            // Convert ByteArray to NSData
            val nsData = dbBytes.toNSData() // <--- Convert ByteArray to NSData
            if (nsData != null) {
                try {
                    // Use NSFileManager's writeToFile or createFileAtPath with NSData
                    // createFileAtPath expects raw data, writeToFile is often safer for files
                    val success = fileManager.createFileAtPath(
                        dbFilePath,
                        nsData, // <--- Pass the NSData object here
                        null
                    )
                    if (success) {
                        println("Successfully copied prepopulated database to: $dbFilePath")
                    } else {
                        println("Failed to copy prepopulated database to: $dbFilePath")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    println("Error copying prepopulated database: ${e.message}")
                }
            } else {
                println("Error converting ByteArray to NSData.")
            }
        } else {
            println("Error reading prepopulated database bytes from shared resources.")
        }
    }
    return Room.databaseBuilder(
        name = dbFileName
    )
}

@OptIn(ExperimentalForeignApi::class)
fun ByteArray.toNSData(): NSData? {
    if (isEmpty()) return NSData()
    return memScoped {
        // Use getPointer() within usePinned to get the raw C pointer
        this@toNSData.usePinned { pinned ->
            NSData.dataWithBytes(pinned.addressOf(0), size.toULong()) // <--- Corrected call
        }
    }
}