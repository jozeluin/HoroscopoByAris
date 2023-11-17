package com.example.horoscapp.ui.detail

import com.example.horoscapp.domain.model.HoroscopeModel

sealed class HoroscopeDetatilState {
    data object Loading:HoroscopeDetatilState()
    data class Error(val error: String):HoroscopeDetatilState()//data class cuando necesita llevar un argumento
    data class Sucess(val prediction: String, val sign:String,val horoscopeModel: HoroscopeModel):
        HoroscopeDetatilState()
}