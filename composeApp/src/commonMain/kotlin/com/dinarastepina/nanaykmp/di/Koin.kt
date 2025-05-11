package com.dinarastepina.nanaykmp.di

import androidx.room.RoomDatabase
import com.dinarastepina.nanaykmp.data.DictionaryDataBase
import com.dinarastepina.nanaykmp.data.getRoomDatabase
import com.dinarastepina.nanaykmp.domain.repository.RussianToNanayRepository
import org.koin.core.context.startKoin
import com.dinarastepina.nanaykmp.data.repository.RussianToNanayRepositoryImpl
import com.dinarastepina.nanaykmp.data.getNanayDao
import com.dinarastepina.nanaykmp.data.getRussianDao
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            platformModule(),
            provideRepositoryModule,
            provideDatabaseModule
        )
    }

val provideDatabaseModule = module {
    single { getRoomDatabase(get<RoomDatabase.Builder<DictionaryDataBase>>()) }
    single { getRussianDao(get()) }
    single { getNanayDao(get()) }
}

val provideRepositoryModule = module {
    includes(provideDatabaseModule)
    singleOf(::RussianToNanayRepositoryImpl).bind(RussianToNanayRepository::class)
}