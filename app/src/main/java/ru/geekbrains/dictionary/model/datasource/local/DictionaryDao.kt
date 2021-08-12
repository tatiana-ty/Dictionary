package ru.geekbrains.dictionary.model.datasource.local

import androidx.room.*
import io.reactivex.Observable
import ru.geekbrains.dictionary.model.entities.TranslationEntity

@Dao
interface DictionaryDao {

    @Query("SELECT * FROM dictionary")
    suspend fun all(): List<TranslationEntity>

    @Query("SELECT * FROM dictionary WHERE word LIKE :word")
    suspend fun getDataByWord(word: String): TranslationEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: TranslationEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<TranslationEntity>)

    @Update
    suspend fun update(entity: TranslationEntity)

    @Delete
    suspend fun delete(entity: TranslationEntity)

}