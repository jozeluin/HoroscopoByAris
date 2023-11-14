package com.example.horoscapp.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getHoroscope(sign: String) {
        viewModelScope.launch {
            _state.value = HoroscopeDetatilState.Loading

            var result = withContext(Dispatchers.IO) { getPredictionUseCase(sign) }//hilo secundario
            Log.i("JoseLuis","HDetailView valor result"+ result?.horoscope)
            if (result != null) {
                _state.value = HoroscopeDetatilState.Sucess(result.horoscope, result.sign)
                Log.i("JoseLuis","HDetailView result=!null "+ result.sign)
            } else {
                _state.value = HoroscopeDetatilState.Error("Error")
                Log.i("JoseLuis","HoroscopeDetatilState Error")
            }
        }

    }

}


