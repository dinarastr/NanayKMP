package com.dinarastepina.nanaykmp.di

import androidx.room.RoomDatabase
import com.dinarastepina.nanaykmp.data.DictionaryDataBase
import org.koin.dsl.module
import com.dinarastepina.nanaykmp.data.getDatabaseBuilder
import org.koin.core.context.startKoin

actual fun platformModule() = module {
    single<RoomDatabase.Builder<DictionaryDataBase>> {
        getDatabaseBuilder()
    }
}

fun initKoin() = startKoin {
    modules(appModule)
}