package com.example.horoscapp.domain.usecase

import com.example.horoscapp.domain.Repository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: Repository) {
    /**
     * Al hacer esto operator invoke, al llamar a esta clase con solo los parentesis
     * GetPredictionUseCase(), ya llamamos a esta funcion
     */
    suspend operator fun invoke(sign: String) = repository.getPrediction(sign)


}