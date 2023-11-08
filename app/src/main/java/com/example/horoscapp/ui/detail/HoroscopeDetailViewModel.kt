package com.example.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor() : ViewModel() {

    private var _state = MutableStateFlow<HoroscopeDetatilState>(HoroscopeDetatilState.Loading)
    val state: StateFlow<HoroscopeDetatilState> = _state


}