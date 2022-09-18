package com.bharti.singh.bookmyshow

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseUser
import java.util.*
import kotlin.collections.HashMap

class RegisterActivity : AppCompatActivity() {

    private lateinit var rootRef : DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseUser: FirebaseUser


    private lateinit var registerBtn: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var isStudioLogin: ToggleButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_acitivity)

        rootRef = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)

        email = findViewById(R.id.reg_emial_input)
        password = findViewById(R.id.reg_password)
        registerBtn = findViewById(R.id.register_activity_btn)
        isStudioLogin = findViewById(R.id.reg_is_studio_btn)

        registerBtn.setOnClickListener {
            register()
        }
    }

    private fun register() {

        val emailStr = email.text.toString()
        val passwordStr = password.text.toString()
        val isStudio = isStudioLogin.isChecked

        if(emailStr.isEmpty() || passwordStr.isEmpty()){
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
        }else if(passwordStr.length<6){
            Toast.makeText(this, "Password length should be more than 6", Toast.LENGTH_SHORT).show()
        } else{
            registerUser(emailStr, passwordStr, isStudio)
        }

    }

    private fun registerUser(email:String, password: String, isStudio:Boolean){

        progressDialog.setMessage("Please wait...")
        progressDialog.show()

        val map = HashMap<String, Any>()
        map["email"] = email
        map["password"] = password
        map["id"] = auth.currentUser!!.uid
        map["isStudioLogin"] = isStudio.toString()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            rootRef.child("Users").child(auth.currentUser!!.uid).setValue(map).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("Register Activity", "on is success")
                    progressDialog.dismiss()
                    Toast.makeText(this@RegisterActivity, "Update profile", Toast.LENGTH_SHORT).show()

                    val intent: Intent = if(isStudio){
                        Intent(this@RegisterActivity, StudioActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    }else{
                        Intent(this@RegisterActivity, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    }
                    startActivity(intent)
                    finish()
                }
            }


        }.addOnFailureListener{
            progressDialog.dismiss()
            Toast.makeText(this@RegisterActivity, it.message, Toast.LENGTH_SHORT).show()

        }

    }

}