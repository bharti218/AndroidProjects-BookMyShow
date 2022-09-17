package com.bharti.singh.bookmyshow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var loginTextView: TextView
    private lateinit var registerTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginTextView = findViewById(R.id.login_btn);
        registerTextView = findViewById(R.id.register_btn);


        loginTextView.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        registerTextView.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterAcitivity::class.java))
        }
    }
}