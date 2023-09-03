package com.unab.desarrollowebymovil.lautipe.lectorqr.network  // Asegúrese de que el paquete coincida con su estructura

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/pagos")  // Reemplace con la URL base de su API
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}
