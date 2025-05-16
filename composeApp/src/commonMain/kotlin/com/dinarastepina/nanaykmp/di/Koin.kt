package com.dinarastepina.nanaykmp.di

import com.dinarastepina.nanaykmp.data.getNanayDao
import com.dinarastepina.nanaykmp.data.getPhraseBookDao
import com.dinarastepina.nanaykmp.data.getRoomDatabase
import com.dinarastepina.nanaykmp.data.getRussianDao
import com.dinarastepina.nanaykmp.data.repository.DataStoreRepositoryImpl
import com.dinarastepina.nanaykmp.data.repository.DictionaryRepositoryImpl
import com.dinarastepina.nanaykmp.data.repository.PhraseBookRepositoryImpl
import com.dinarastepina.nanaykmp.domain.repository.DataStoreRepository
import com.dinarastepina.nanaykmp.domain.repository.DictionaryRepository
import com.dinarastepina.nanaykmp.domain.repository.PhraseBookRepository
import com.dinarastepina.nanaykmp.presentation.phrasebook.PhrasesViewModel
import com.dinarastepina.nanaykmp.presentation.phrasebook.TopicsViewModel
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
    single { getPhraseBookDao(get()) }
}

val repositoryModule = module {
    singleOf(::DictionaryRepositoryImpl).bind(DictionaryRepository::class)
    singleOf(::PhraseBookRepositoryImpl).bind(PhraseBookRepository::class)
    single<DataStoreRepository> { DataStoreRepositoryImpl(get()) }
}



val viewModelModule = module {
    factory { DictionaryViewModel(get(), get()) }
    factory { TopicsViewModel(get() ) }
    factory { PhrasesViewModel() }
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

