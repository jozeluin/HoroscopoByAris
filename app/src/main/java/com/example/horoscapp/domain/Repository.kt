package com.example.horoscapp.domain


import com.example.horoscapp.domain.model.PredictionModel

/**
 * Comunicacion entre capa de data y dominio
 */
interface   Repository {
    suspend fun getPrediction(sign:String):PredictionModel?
}