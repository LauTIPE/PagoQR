package com.unab.desarrollowebymovil.lautipe.lectorqr

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.unab.desarrollowebymovil.lautipe.lectorqr.`interface`.ApiInterface
import com.unab.desarrollowebymovil.lautipe.lectorqr.model.PaymentInfo
import com.unab.desarrollowebymovil.lautipe.lectorqr.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        // Inicializar Retrofit
        val api = ApiClient.getClient().create(ApiInterface::class.java)

        // Obtener el resultado del escaneo desde el Intent
        val scanResult: String? = intent.getStringExtra("SCAN_RESULT")

        // Identificar los TextViews para mostrar los datos
        val transactionTextView: TextView = findViewById(R.id.textView10)
        val amountTextView: TextView = findViewById(R.id.textView9)
        val dateTextView: TextView = findViewById(R.id.textView8)

        // Realizar la llamada a la API para obtener los detalles del pago
        api.getPaymentInfo(scanResult).enqueue(object : Callback<PaymentInfo> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<PaymentInfo>, response: Response<PaymentInfo>) {
                if (response.isSuccessful) {
                    val paymentInfo = response.body()
                    // Actualizar los TextViews con los datos recibidos
                    transactionTextView.text = "Nro. transacción: ${paymentInfo?.idTrx}"
                    amountTextView.text = "Monto: ${paymentInfo?.monto}"
                    dateTextView.text = "Fecha: ${paymentInfo?.fecha}"
                } else {
                    // Manejar error
                    Toast.makeText(this@PayActivity, "Error al obtener los detalles", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PaymentInfo>, t: Throwable) {
                // Manejar falla en la red u otro error
                Toast.makeText(this@PayActivity, "Fallo en la conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
