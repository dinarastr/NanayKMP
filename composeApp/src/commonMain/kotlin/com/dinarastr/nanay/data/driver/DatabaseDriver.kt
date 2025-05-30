package com.dinarastr.nanay.data.driver

import androidx.sqlite.SQLiteDriver

expect class DatabaseDriver {
    fun createDriver(): SQLiteDriver
} 