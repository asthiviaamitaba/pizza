package com.example.pizzaapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val txtEmail: EditText = findViewById(R.id.registerEmail)
        val txtName: EditText = findViewById(R.id.registerPersonName)
        val txtLevel: EditText = findViewById(R.id.registerLevel)
        val txtPassword: EditText = findViewById(R.id.registerPassword)
        //instance button register
        val btnRegister: Button = findViewById(R.id.buttonRegisterAccount)

        btnRegister.setOnClickListener{
            //object class databaseHelper
            val databaseHelper = DatabaseHelper (this)
            //declare data
            val email: String = txtEmail.text.toString().trim()
            val name: String = txtName.text.toString().trim()
            val level: String = txtLevel.text.toString().trim()
            val password: String = txtPassword.text.toString().trim()

            //check data --> email sudah terdaftar atau belum
            val data: String = databaseHelper.checkData(email)
            //jika belum terdaftar
            if(data == "") { //data == null (karena tidak bisa, makanya pakai tanda petik)
                //insert data
                databaseHelper.addAccount (
                    email, name, level, password)

                //show LoginActivity
                val intentLogin = Intent(this@RegisterActivity,
                    LoginActivity::class.java)
                startActivity(intentLogin)
            }
            else {
                Toast.makeText(this@RegisterActivity, "Register Failed." +
                        "Your email alredy registered", Toast.LENGTH_SHORT)
            }
        }

    }
}