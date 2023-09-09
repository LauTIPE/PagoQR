package com.unab.desarrollowebymovil.lautipe.lectorqr.network  // Asegúrese de que el paquete coincida con su estructura

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
//        TODO("CAMBIAR URL APICLIENT POR URL IPv4 DE SU PROPIO EQUIPO")
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.2.102:8080/api/pagos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}
