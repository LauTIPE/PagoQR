package com.unab.desarrollowebymovil.lautipe.lectorqr.`interface`

import com.unab.desarrollowebymovil.lautipe.lectorqr.model.PaymentInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiInterface {
    @GET("{ID_Pago}")
    fun getPaymentInfo(@Path("ID_Pago") id: String?): Call<PaymentInfo>

    @PUT("{ID_Pago}/aprobar")
    fun aprovePayment(@Path("ID_Pago") id: String?): Call<Void>

    @PUT("{ID_Pago}/rechazar")
    fun rejectPayment(@Path("ID_Pago") id: String?): Call<Void>
}
