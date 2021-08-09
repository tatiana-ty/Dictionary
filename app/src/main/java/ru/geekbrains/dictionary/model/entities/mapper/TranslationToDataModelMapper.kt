package ru.geekbrains.dictionary.model.entities.mapper

import ru.geekbrains.dictionary.model.entities.DataModel
import ru.geekbrains.dictionary.model.entities.Meanings
import ru.geekbrains.dictionary.model.entities.Translation
import ru.geekbrains.dictionary.model.entities.TranslationEntity
import ru.geekbrains.dictionary.model.entities.mapper.BaseMapper


object TranslationToDataModelMapper : BaseMapper<TranslationEntity, DataModel> {
    override fun map(type: TranslationEntity?): DataModel {
        return DataModel(
            text = type?.rus,
            meanings = listOf(Meanings(Translation(type?.en), type?.image)),
        )
    }
}