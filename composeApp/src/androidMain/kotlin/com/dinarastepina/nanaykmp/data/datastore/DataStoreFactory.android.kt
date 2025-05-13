package com.dinarastepina.nanaykmp.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "nanay.preferences_pb")

fun createDataStore(context: Context): DataStore<Preferences> = context.dataStore