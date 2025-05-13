package com.dinarastepina.nanaykmp.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import com.dinarastepina.nanaykmp.data.DictionaryDataBase
import com.dinarastepina.nanaykmp.data.datastore.createDataStore
import org.koin.dsl.module
import com.dinarastepina.nanaykmp.data.getDatabaseBuilder
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