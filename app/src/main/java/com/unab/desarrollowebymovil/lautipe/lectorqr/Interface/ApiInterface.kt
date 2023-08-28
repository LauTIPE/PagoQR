package com.unab.desarrollowebymovil.lautipe.lectorqr.Interface

import com.unab.desarrollowebymovil.lautipe.lectorqr.model.PaymentInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("api/getPaymentInfo/{ID_Pago}")
    fun getPaymentInfo(@Path("ID_Pago") id: Int): Call<PaymentInfo>
}
