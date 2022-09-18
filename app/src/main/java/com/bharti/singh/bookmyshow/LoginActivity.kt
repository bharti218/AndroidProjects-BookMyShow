package com.bharti.singh.bookmyshow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var email: EditText;
    private lateinit var password: EditText;

    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.login_email_input)
        password = findViewById(R.id.login_password)
        loginBtn = findViewById(R.id.activity_login_btn)
        firebaseAuth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            login()
        }
    }

    private fun login() {

        val emailTxt = email.text.toString()
        val passwordTxt = password.text.toString()

        if (emailTxt.isEmpty() || passwordTxt.isEmpty())
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show()
        else
            loginUser(emailTxt, passwordTxt)
    }

    private fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
            }
        }.addOnFailureListener {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }
    }
}