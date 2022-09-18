package com.bharti.singh.bookmyshow

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import java.util.*
import kotlin.collections.HashMap

class RegisterAcitivity : AppCompatActivity() {

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
        isStudioLogin = findViewById(R.id.reg_is_studio_btn);

        registerBtn.setOnClickListener {
            register()
        }

        auth.addAuthStateListener {
            if(it.currentUser==null){

            }else{
                firebaseUser = it.currentUser!!
            }

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
            registerUser(emailStr, passwordStr, isStudio);
        }

    }

    private fun registerUser(email:String, password: String, isStudio:Boolean){

        progressDialog.setMessage("Please wait...")
        progressDialog.show()

        val map = HashMap<String, Any>()
        map["email"] = email
        map["password"] = password
        map["id"] = auth.currentUser!!.uid;
        map["isStudioLogin"] = isStudio.toString()

        Toast.makeText(this@RegisterAcitivity, "Map size = $map.size", Toast.LENGTH_LONG).show()



        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            rootRef.child("Users").child(auth.currentUser!!.uid).setValue(map).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("Register Activity", "on is success")
                    progressDialog.dismiss()
                    Toast.makeText(this@RegisterAcitivity, "Update profile", Toast.LENGTH_SHORT).show()
                    startActivity(
                        Intent(
                            this@RegisterAcitivity,
                            MainActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                    finish()
                }
            }


        }.addOnFailureListener{
            progressDialog.dismiss()
            Toast.makeText(this@RegisterAcitivity, it.message, Toast.LENGTH_SHORT).show()

        }

    }

}