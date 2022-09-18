package com.bharti.singh.bookmyshow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
                FirebaseDatabase.getInstance().reference.child("Users")
                    .child(FirebaseAuth.getInstance().currentUser!!.uid)
                    .addListenerForSingleValueEvent(
                        object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val isStudio = snapshot.child("isStudioLogin").getValue(String::class.java).toString()

                                val intent = if(isStudio == "true"){
                                    Intent(this@LoginActivity, StudioActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                }else{
                                    Intent(this@LoginActivity, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                }
                                startActivity(intent)
                                finish()
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Toast.makeText(this@LoginActivity, "cancelled", Toast.LENGTH_LONG).show()
                            }
                        }
                    )

            }
        }.addOnFailureListener {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }
    }
}