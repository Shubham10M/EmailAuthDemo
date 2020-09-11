package com.internshala.emailauthdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        val btnLogin = findViewById<View>(R.id.btnLogin) as Button
        val registxt = findViewById<View>(R.id.registxt) as TextView

        registxt.setOnClickListener(View.OnClickListener {
            view -> Register()
        })
        btnLogin.setOnClickListener(View.OnClickListener {
           view -> login()
        })

    }
     private fun login(){
         val email = etEmail.text.toString()
         val password = etPassword.text.toString()
         if(!email.isEmpty() && !password.isEmpty()){
             // get access to an instance of FirebaseAuth and perform login
             FirebaseAuth.getInstance()
                 .signInWithEmailAndPassword(email, password)
                 .addOnSuccessListener {
                     // login successful, update the UI
                     Log.e("TAG", "--------------------success---------------"+it.user?.email)
                 }
                 .addOnFailureListener {
                     // login failed, probably bad email-password combo or a network issue
                     it.printStackTrace()
                     Log.e("TAG", "--------error---------------------------"+it.message)

                 }
                 .addOnCompleteListener {

                 }
         }else{
             Toast.makeText(this, " Please fill up the credentials", Toast.LENGTH_LONG).show()
         }
     }
    private fun Register(){
        startActivity(Intent(this, RegisterActivity::class.java))
    }
    }