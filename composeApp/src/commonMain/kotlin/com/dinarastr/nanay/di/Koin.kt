package com.dinarastr.nanay.di

import com.dinarastr.nanay.audio.AudioPlayerFactory
import com.dinarastr.nanay.audio.PlaylistManager
import com.dinarastr.nanay.data.getNanayDao
import com.dinarastr.nanay.data.getPhraseBookDao
import com.dinarastr.nanay.data.getRoomDatabase
import com.dinarastr.nanay.data.getRussianDao
import com.dinarastr.nanay.data.repository.DataStoreRepositoryImpl
import com.dinarastr.nanay.data.repository.DictionaryRepositoryImpl
import com.dinarastr.nanay.data.repository.PhraseBookRepositoryImpl
import com.dinarastr.nanay.domain.repository.DataStoreRepository
import com.dinarastr.nanay.domain.repository.DictionaryRepository
import com.dinarastr.nanay.domain.repository.PhraseBookRepository
import com.dinarastr.nanay.presentation.phrasebook.PhrasesViewModel
import com.dinarastr.nanay.presentation.phrasebook.TopicsViewModel
import com.dinarastr.nanay.presentation.dictionary.DictionaryViewModel
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
    factory { PhrasesViewModel(get(), get()) }
}

val audioModule = module {
    single { AudioPlayerFactory() }
    single { PlaylistManager(get()) }
}

val commonModule = module {
    includes(
        databaseModule,
        repositoryModule,
        viewModelModule,
        audioModule
    )
}

val appModule = module {
    includes(commonModule, platformModule())
}

