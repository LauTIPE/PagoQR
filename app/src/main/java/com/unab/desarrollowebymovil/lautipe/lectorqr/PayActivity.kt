
package com.unab.desarrollowebymovil.lautipe.lectorqr

import android.content.Intent
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
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

        // Initialize buttons
        val approveButton: Button = findViewById(R.id.button4)
        val rejectButton: Button = findViewById(R.id.button5)

        // Initialize Retrofit
        val api = ApiClient.getClient().create(ApiInterface::class.java)

        // Get the scan result from the Intent
        val scanResult: String? = intent.getStringExtra("SCAN_RESULT")

        // Identify the TextViews to display data
        val transactionTextView: TextView = findViewById(R.id.textView10)
        val amountTextView: TextView = findViewById(R.id.textView9)
        val dateTextView: TextView = findViewById(R.id.textView8)

        // Make the API call to get payment details
        api.getPaymentInfo(scanResult).enqueue(object : Callback<PaymentInfo> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<PaymentInfo>, response: Response<PaymentInfo>) {
                if (response.isSuccessful) {
                    val paymentInfo = response.body()
                    // Update the TextViews with the received data
                    transactionTextView.text = "Nro. transacción: ${paymentInfo?.idTrx}"
                    amountTextView.text = "Monto: ${paymentInfo?.monto}"
                    dateTextView.text = "Fecha: ${paymentInfo?.fecha}"
                } else {
                    // Handle error
                    Toast.makeText(this@PayActivity, "Error al obtener los detalles", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PaymentInfo>, t: Throwable) {
                // Handle network failure or other error
                Toast.makeText(this@PayActivity, "Fallo en la conexión", Toast.LENGTH_SHORT).show()
            }
        })

        // Set click listener for the approve button
        approveButton.setOnClickListener {
            val intent = Intent(this, PaymentConfirmationActivity::class.java)
            startActivity(intent)
        }

        // Set click listener for the reject button
        rejectButton.setOnClickListener {
            val intent = Intent(this, PaymentRejectionActivity::class.java)
            startActivity(intent)
        }
    }
}
