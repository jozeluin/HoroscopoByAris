package com.example.horoscapp.ui.horoscope

import com.example.horoscapp.data.providers.HoroscopeProvider
import com.example.horoscapp.motherObject.HoroscopeMotherObject.horoscopeInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK

import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class HorosopeViewModelTest{

    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var viewModel: HorosopeViewModel

    @Before
    fun setup(){
        MockKAnnotations.init(this, relaxUnitFun = true)


    }

    @Test
    fun `when viewModel is created then horoscopes are loaded`(){
        every{horoscopeProvider.getHoroscope()} returns horoscopeInfoList
       viewModel=HorosopeViewModel(horoscopeProvider)

        val horoscopes=viewModel.horoscope.value

        assertTrue(horoscopes.isNotEmpty())
    }

}
