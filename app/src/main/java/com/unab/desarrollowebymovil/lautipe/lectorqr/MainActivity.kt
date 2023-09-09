package com.unab.desarrollowebymovil.lautipe.lectorqr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.unab.desarrollowebymovil.lautipe.lectorqr.`interface`.ApiInterface
import com.unab.desarrollowebymovil.lautipe.lectorqr.model.PaymentInfo
import com.unab.desarrollowebymovil.lautipe.lectorqr.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scanButton: Button = findViewById(R.id.scanButton)
        val logoutButton: Button = findViewById(R.id.logoutButton)

        scanButton.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt("Escanea el código QR")
            integrator.setOrientationLocked(false)
            integrator.setCameraId(0)
            integrator.setBeepEnabled(true)
            integrator.setBarcodeImageEnabled(true)
            integrator.initiateScan()
        }

        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
            } else {
                val scannedId = result.contents

                if (scannedId != null) {
                    val api = ApiClient.getClient().create(ApiInterface::class.java)
                    val call = api.getPaymentInfo(scannedId)

                    call.enqueue(object : Callback<PaymentInfo> {
                        override fun onResponse(call: Call<PaymentInfo>, response: Response<PaymentInfo>) {
                            if (response.isSuccessful) {
                                val paymentInfo = response.body();
                                val intent = Intent(this@MainActivity, PayActivity::class.java)
                                intent.putExtra("paymentInfo", paymentInfo)
                                startActivity(intent)
                            }
                        }

                        override fun onFailure(call: Call<PaymentInfo>, t: Throwable) {
                            Log.e("on.scan.fail", "cause", t)
                            Toast.makeText(
                                this@MainActivity,
                                "Error al obtener información del pago",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                } else {
                    Toast.makeText(this, "El valor escaneado no es válido", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
