package com.example.horoscapp.data.network.response

import io.kotlintest.shouldBe
import org.junit.Assert.*
import org.junit.Test
import com.example.horoscapp.motherObject.HoroscopeMotherObject
import com.example.horoscapp.motherObject.HoroscopeMotherObject.anyResponse

class PredictionResponseTest{
    @Test
    fun `toDomain should return a correct PredictionModel`(){
        //Given
        val horoscopeResponse = anyResponse

        //When
        val predictionModel=horoscopeResponse.toDomain()
        //Then
        predictionModel.sign shouldBe  horoscopeResponse.sign
        predictionModel.horoscope shouldBe  horoscopeResponse.horoscope
    }

}