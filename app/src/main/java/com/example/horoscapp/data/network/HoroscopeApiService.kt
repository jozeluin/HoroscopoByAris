package com.example.horoscapp.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.math.sign

interface HoroscopeApiService {

    @GET("/{sign}) ")
    suspend fun getHoroscope(@Path("sign") sign: String) {

    }


}