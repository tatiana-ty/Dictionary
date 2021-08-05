package ru.geekbrains.dictionary.model.datasource.local

import io.reactivex.Observable
import androidx.room.Dao
import androidx.room.Query
import ru.geekbrains.dictionary.model.entities.TranslationEntity

@Dao
interface DictionaryDao {

    @Query("SELECT * FROM dictionary WHERE rus IN (:word)")
    suspend fun search(word: String): List<TranslationEntity>

}