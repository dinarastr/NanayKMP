package com.dinarastepina.nanaykmp.data.driver

import androidx.sqlite.SQLiteDriver

expect class DatabaseDriver {
    fun createDriver(): SQLiteDriver
} 