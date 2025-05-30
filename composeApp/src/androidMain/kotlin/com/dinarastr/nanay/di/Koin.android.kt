package com.dinarastr.nanay.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import com.dinarastr.nanay.data.DictionaryDataBase
import com.dinarastr.nanay.data.datastore.createDataStore
import com.dinarastr.nanay.data.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
    single<RoomDatabase.Builder<DictionaryDataBase>> {
        getDatabaseBuilder(get())
    }
    single<DataStore<Preferences>> {
        createDataStore(get())
    }
}
