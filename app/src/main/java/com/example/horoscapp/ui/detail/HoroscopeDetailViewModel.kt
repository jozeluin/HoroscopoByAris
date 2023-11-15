package com.example.horoscapp.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) :
    ViewModel() {

    private var _state = MutableStateFlow<HoroscopeDetatilState>(HoroscopeDetatilState.Loading)
    val state: StateFlow<HoroscopeDetatilState> = _state

    lateinit var horoscope: HoroscopeModel
    fun getHoroscope(sign: String) {

        viewModelScope.launch {
            _state.value = HoroscopeDetatilState.Loading
            //hilo principal
            val result = withContext(Dispatchers.IO) { getPredictionUseCase(sign) }//hilo secundario
            //hilo principal
            if (result != null) {
                _state.value = HoroscopeDetatilState.Sucess(result.horoscope, result.sign)
            } else {
                _state.value = HoroscopeDetatilState.Error("Ha ocurrido un error")
                Log.i("HoroscopeDetailViewModel","Ha ocurrido un error")
            }

        }

    }


}