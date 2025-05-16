package com.dinarastepina.nanaykmp.data

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import com.dinarastepina.nanaykmp.data.dao.NanayDao
import com.dinarastepina.nanaykmp.data.dao.PhraseBookDao
import com.dinarastepina.nanaykmp.data.dao.RussianDao
import com.dinarastepina.nanaykmp.data.models.NanayWord
import com.dinarastepina.nanaykmp.data.models.Phrase
import com.dinarastepina.nanaykmp.data.models.PhraseTopic
import com.dinarastepina.nanaykmp.data.models.RussianWord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [
    NanayWord::class,
    RussianWord::class,
    PhraseTopic::class,
    Phrase::class
 ], version = 3, exportSchema = true)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class DictionaryDataBase: RoomDatabase() {
    abstract fun getNanayDao(): NanayDao
    abstract fun getRussianDao(): RussianDao
    abstract fun getPhraseBookDao(): PhraseBookDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<DictionaryDataBase> {
    override fun initialize(): DictionaryDataBase
}

private val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS 'russian_to_nanay' ('id' INTEGER NOT NULL, " +
                "'russian' TEXT NOT NULL, 'nanay' TEXT NOT NULL, " + "PRIMARY KEY('id'))")
    }
}

private val migration_2_3 = object : Migration(2, 3) {
    override fun migrate(connection: SQLiteConnection) {
        connection.execSQL("ALTER TABLE talysh_to_russian RENAME TO nanay_to_russian")
        connection.execSQL("ALTER TABLE nanay_to_russian RENAME COLUMN talysh TO nanay")
        connection.execSQL("CREATE TABLE IF NOT EXISTS 'phrases_topics' ('id' INTEGER NOT NULL, " +
                "'title' TEXT NOT NULL, 'imageRes' TEXT NOT NULL, " + "PRIMARY KEY('id'))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS 'phrases' ('id' INTEGER NOT NULL, " +
                "'topicId' INTEGER NOT NULL, 'originalText' TEXT NOT NULL, 'translation' TEXT NOT NULL, " +
                "'audioRes' TEXT NOT NULL, " + "PRIMARY KEY('id'))")
    }
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<DictionaryDataBase>
): DictionaryDataBase {
    return builder
        .addMigrations(migration_1_2, migration_2_3)
        .fallbackToDestructiveMigration(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

fun getRussianDao(appDatabase: DictionaryDataBase) = appDatabase.getRussianDao()
fun getNanayDao(appDatabase: DictionaryDataBase) = appDatabase.getNanayDao()
fun getPhraseBookDao(appDatabase: DictionaryDataBase) = appDatabase.getPhraseBookDao()
