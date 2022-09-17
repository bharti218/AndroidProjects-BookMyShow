package com.bharti.singh.bookmyshow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ToggleButton

class RegisterAcitivity : AppCompatActivity() {

    private lateinit var registerBtn: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var isStudioLogin: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_acitivity)

        email = findViewById(R.id.reg_emial_input)
        password = findViewById(R.id.reg_password)
        registerBtn = findViewById(R.id.register_activity_btn)
        isStudioLogin = findViewById(R.id.reg_is_studio_btn);

        registerBtn.setOnClickListener {
            register()
        }
    }

    private fun register() {

        startActivity(
            Intent(
                this,
                MainActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

}