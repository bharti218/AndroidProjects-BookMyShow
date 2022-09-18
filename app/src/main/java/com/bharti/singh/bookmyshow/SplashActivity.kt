package com.bharti.singh.bookmyshow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SplashActivity : AppCompatActivity() {

    private lateinit var loginTextView: TextView
    private lateinit var registerTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        loginTextView = findViewById(R.id.login_btn)
        registerTextView = findViewById(R.id.register_btn)


        loginTextView.setOnClickListener {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }

        registerTextView.setOnClickListener {
            startActivity(Intent(this@SplashActivity, RegisterActivity::class.java))
        }
    }
}