package com.dinarastepina.nanaykmp.di

import com.dinarastepina.nanaykmp.data.datastore.DataStoreFactory
import com.dinarastepina.nanaykmp.data.datastore.IOSDataStoreFactory
import org.koin.dsl.module

val platformModule = module {
    // ... existing platform dependencies ...
    single<DataStoreFactory> { IOSDataStoreFactory() }
} 