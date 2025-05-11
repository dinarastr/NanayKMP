package com.dinarastepina.nanaykmp.di

import androidx.room.RoomDatabase
import com.dinarastepina.nanaykmp.data.DictionaryDataBase
import com.dinarastepina.nanaykmp.data.getDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun platformModule() = module {
    includes(commonModule())
    single<RoomDatabase.Builder<DictionaryDataBase>> {
        getDatabaseBuilder(androidContext())
    }
}
