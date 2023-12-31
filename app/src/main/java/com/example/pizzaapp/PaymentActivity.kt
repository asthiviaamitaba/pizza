package com.example.pizzaapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val txtTotal: TextView = findViewById(R.id.textViewTotalPurchase)
        val txtChange: TextView = findViewById(R.id.textViewChange)
        val txtCash: EditText = findViewById(R.id.editTextCash)
        val btnFinish: Button = findViewById(R.id.buttonFinish)

        txtTotal.text = (TransaksiAdapter.harga + (TransaksiAdapter.harga * 0.10)).toString()
        txtChange.text = "0"
        btnFinish.setOnClickListener {
            val kembali = (txtCash.text.toString().toDouble()) - (txtTotal.text.toString().toDouble())
            txtChange.setText(kembali.toString())
            //kosongkan list
            TransaksiAdapter.listId.clear()
            TransaksiAdapter.listNama.clear()
            TransaksiAdapter.listHarga.clear()
            TransaksiAdapter.listJumlah.clear()
            TransaksiAdapter.listFoto.clear()
            TransaksiAdapter.harga = 0
            TransaksiAdapter.jumlah = 0

            val intentMain = Intent(this@PaymentActivity,MainActivity::class.java)
            startActivity(intentMain)

        }
    }
}
