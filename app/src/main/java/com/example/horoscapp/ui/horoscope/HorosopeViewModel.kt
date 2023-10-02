package com.example.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeInfo.Aries
import com.example.horoscapp.domain.model.HoroscopeInfo.Gemini
import com.example.horoscapp.domain.model.HoroscopeInfo.Taurus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class HorosopeViewModel @Inject constructor():ViewModel() {

    private var _horoscope= MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscope :StateFlow<List<HoroscopeInfo>> =_horoscope

    init {
        _horoscope.value= listOf(
            Aries,Taurus,Gemini,
            Aries,Taurus,Gemini,
            Aries,Taurus,Gemini,
            Aries,Taurus,Gemini
        )
    }


}