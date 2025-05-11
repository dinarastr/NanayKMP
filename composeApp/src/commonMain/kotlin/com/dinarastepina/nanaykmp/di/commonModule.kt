package com.dinarastepina.nanaykmp.di

import com.dinarastepina.nanaykmp.domain.repository.RussianToNanayRepository
import com.dinarastepina.nanaykmp.presentation.dictionary.DictionaryViewModel
import org.koin.dsl.module

fun commonModule() = module {
    includes(provideRepositoryModule)
    factory { DictionaryViewModel(get()) } // Use 'factory' for ViewModels
}