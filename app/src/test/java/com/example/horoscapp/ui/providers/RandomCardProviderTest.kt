package com.example.horoscapp.ui.providers

import org.junit.Assert.*
import org.junit.Test

//control+shift+t  encima del nombre de la clase, para que te salga la clasetest con todo su directorio
//dentro del directorio test
class RandomCardProviderTest{

    @Test
    fun`getRandomCard should return a random Card`(){

        val ramdonCard = RandomCardProvider()

        val card=ramdonCard.getLucky()

        assertNotNull(card)//los assert son como un "if" en testing

    }
}