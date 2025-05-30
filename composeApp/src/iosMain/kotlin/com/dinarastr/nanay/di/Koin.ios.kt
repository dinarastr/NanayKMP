package com.dinarastr.nanay.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import com.dinarastr.nanay.data.DictionaryDataBase
import com.dinarastr.nanay.data.datastore.createDataStore
import org.koin.dsl.module
import com.dinarastr.nanay.data.getDatabaseBuilder
import org.koin.core.context.startKoin

actual fun platformModule() = module {
    single<RoomDatabase.Builder<DictionaryDataBase>> {
        getDatabaseBuilder()
    }
    single<DataStore<Preferences>> {
        createDataStore()
    }
}

fun initKoin() = startKoin {
    modules(appModule)
}