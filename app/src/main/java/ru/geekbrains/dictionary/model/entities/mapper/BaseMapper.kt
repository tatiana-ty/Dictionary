package ru.geekbrains.dictionary.model.entities.mapper

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}