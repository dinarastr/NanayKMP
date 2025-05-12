package com.dinarastepina.nanaykmp.di

import com.dinarastepina.nanaykmp.data.getNanayDao
import com.dinarastepina.nanaykmp.data.getRoomDatabase
import com.dinarastepina.nanaykmp.data.getRussianDao
import com.dinarastepina.nanaykmp.data.repository.RussianToNanayRepositoryImpl
import com.dinarastepina.nanaykmp.domain.repository.RussianToNanayRepository
import com.dinarastepina.nanaykmp.presentation.dictionary.DictionaryViewModel
import org.koin.core.context.startKoin
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
            appModule
        )
    }

val databaseModule = module {
    single { getRoomDatabase(get()) }
    single { getRussianDao(get()) }
    single { getNanayDao(get()) }
}

val repositoryModule = module {
    singleOf(::RussianToNanayRepositoryImpl).bind(RussianToNanayRepository::class)
}

val viewModelModule = module {
    factory { DictionaryViewModel(get()) }
}

val commonModule = module {
    includes(
        databaseModule,
        repositoryModule,
        viewModelModule
    )
}

val appModule = module {
    includes(commonModule, platformModule())
}

