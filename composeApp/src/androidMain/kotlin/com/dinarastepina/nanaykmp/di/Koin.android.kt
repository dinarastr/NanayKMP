package com.dinarastepina.nanaykmp.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import com.dinarastepina.nanaykmp.data.DictionaryDataBase
import com.dinarastepina.nanaykmp.data.datastore.createDataStore
import com.dinarastepina.nanaykmp.data.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
    single<RoomDatabase.Builder<DictionaryDataBase>> {
        getDatabaseBuilder(get())
    }
    single<DataStore<Preferences>> {
        createDataStore(get())
    }
}
