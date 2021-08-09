package ru.geekbrains.dictionary.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
class TranslationEntity(
    @PrimaryKey
    @ColumnInfo(name = "rus")
    val rus: String,
    @ColumnInfo(name = "en")
    val en: String,
    @ColumnInfo(name = "image")
    val image: String
)