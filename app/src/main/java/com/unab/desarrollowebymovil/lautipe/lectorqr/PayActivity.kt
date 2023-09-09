package com.unab.desarrollowebymovil.lautipe.lectorqr

import android.content.Intent
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.unab.desarrollowebymovil.lautipe.lectorqr.`interface`.ApiInterface
import com.unab.desarrollowebymovil.lautipe.lectorqr.model.PaymentInfo
import com.unab.desarrollowebymovil.lautipe.lectorqr.network.ApiClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        @Suppress("DEPRECATION")
        val paymentInfo = intent.getSerializableExtra("paymentInfo") as PaymentInfo
        val idPago = paymentInfo.idTrx.toString()

        val transactionTextView: TextView = findViewById(R.id.textView10)
        transactionTextView.text = "Nro. transacción: ${paymentInfo.idTrx}"

        val amountTextView: TextView = findViewById(R.id.textView9)
        amountTextView.text = "Monto: ${paymentInfo.monto}"

        val dateTextView: TextView = findViewById(R.id.textView8)
        dateTextView.text = "Fecha: ${paymentInfo.fecha}"

        val approveButton: Button = findViewById(R.id.button4)
        val rejectButton: Button = findViewById(R.id.button5)
        val api = ApiClient.getClient().create(ApiInterface::class.java)

        approveButton.setOnClickListener {
            api.aprovePayment(idPago).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        val intent = Intent(this@PayActivity, PaymentConfirmationActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("approve.error", "Ocurrio un error al aprobar el pago", t)
                    Toast.makeText(this@PayActivity, "Fallo en la conexión", Toast.LENGTH_SHORT).show()
                }
            })
        }

        rejectButton.setOnClickListener {
            api.rejectPayment(idPago).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        val intent = Intent(this@PayActivity, PaymentRejectionActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("reject.error", "Ocurrio un error al rechazar el pago", t)
                    Toast.makeText(this@PayActivity, "Fallo en la conexión", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
