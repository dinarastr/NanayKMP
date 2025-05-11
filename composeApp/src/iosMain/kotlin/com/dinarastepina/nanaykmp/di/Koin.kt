package com.dinarastepina.nanaykmp.di

import androidx.room.RoomDatabase
import com.dinarastepina.nanaykmp.data.DictionaryDataBase
import org.koin.dsl.module
import com.dinarastepina.nanaykmp.data.getDatabaseBuilder

actual fun platformModule() = module {
    includes(commonModule())
    single<RoomDatabase.Builder<DictionaryDataBase>> {
        getDatabaseBuilder()
    }
}