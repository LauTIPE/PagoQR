package com.unab.desarrollowebymovil.lautipe.lectorqr.model

data class PaymentInfo(
    val ID_Pago: Int,
    val ID_llave: String,
    val Id_Sucursal: Int,
    val ID_Pos: Int,
    val fecha: String,
    val Cajero: Int,
    val Trx: Int,
    val Tipo_trx: String,
    val Monto: Int,
    val Cod_respuesta: Int,
    val Estado: String
)
