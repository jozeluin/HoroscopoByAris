package com.example.horoscapp.data.core.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Interceptor autorizacion
 * Agregamos a la cadena de pedido al servidior nueva informacion
 */
class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {


        val request = chain.request()
            .newBuilder()
            .header("Autorizacion", tokenManager.getToken())
            .build()
        return chain.proceed(request)
    }
}

class TokenManager @Inject constructor() {
    fun getToken(): String {
        val string: String
        string = "Suscribete"
        return string
    }

}