package com.example.horoscapp.ui.detail

sealed class HoroscopeDetatilState {
    data object Loading:HoroscopeDetatilState()
    data class Error(val error: String):HoroscopeDetatilState()//data class cuando necesita llevar un argumento
    data class Sucess(val data: String):HoroscopeDetatilState()
}