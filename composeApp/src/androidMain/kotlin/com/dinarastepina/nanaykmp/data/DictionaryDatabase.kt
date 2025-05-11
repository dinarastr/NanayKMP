package com.dinarastepina.nanaykmp.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import nanaykmp.composeapp.generated.resources.Res

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<DictionaryDataBase> {
    val appContext = ctx.applicationContext
    return Room.databaseBuilder<DictionaryDataBase>(
        context = appContext,
        name = "nanay_dictionary"
    ).createFromAsset(Res.getUri("files/talysh_to_russian"))
}