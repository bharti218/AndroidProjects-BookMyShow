package com.bharti.singh.bookmyshow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var email: EditText;
    private lateinit var password: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.login_email_input)
        password = findViewById(R.id.login_password)
        loginBtn = findViewById(R.id.activity_login_btn)

        loginBtn.setOnClickListener {
            login()
        }
    }

    private fun login() {
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }
}