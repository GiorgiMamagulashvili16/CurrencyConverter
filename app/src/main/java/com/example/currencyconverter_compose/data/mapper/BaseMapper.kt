package com.example.currencyconverter_compose.data.mapper

interface BaseMapper<in MODEL_A, out MODEL_B> {

    fun mapToModel(model: MODEL_A): MODEL_B

    fun mapToList(list: List<MODEL_A>): List<MODEL_B> {
        return list.map { mapToModel(it) }
    }
}