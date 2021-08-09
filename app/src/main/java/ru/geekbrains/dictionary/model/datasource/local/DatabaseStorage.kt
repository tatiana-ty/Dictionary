package ru.geekbrains.dictionary.model.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.dictionary.model.entities.TranslationEntity

@Database(
    entities = [TranslationEntity::class],
    version = 1
)
abstract class DatabaseStorage : RoomDatabase() {

    abstract val dictionaryDao: DictionaryDao

    companion object {
        const val DICTIONARY_DATABASE = "DICTIONARY_DATABASE"
    }

}