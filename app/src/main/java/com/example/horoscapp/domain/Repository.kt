package com.example.horoscapp.domain

/**
 * Comunicacion entre capa de data y dominio
 */
interface Repository {
    suspend fun getPrediction(sign:String)
}