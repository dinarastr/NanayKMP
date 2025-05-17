package com.dinarastepina.nanaykmp.data.driver

import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi

actual class DatabaseDriver {
    actual fun createDriver(): SQLiteDriver {
        return BundledSQLiteDriver()
    }
} 