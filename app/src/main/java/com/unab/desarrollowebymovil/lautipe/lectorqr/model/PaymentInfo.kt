package com.unab.desarrollowebymovil.lautipe.lectorqr.model

data class PaymentInfo(
    val idTrx: Int,
    val tipoTrx: String,
    val monto: Int,
    val fecha: String,
    val estado: String
)
