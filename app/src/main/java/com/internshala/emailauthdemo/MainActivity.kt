package com.internshala.emailauthdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
    }
    override fun onStart() {
        super.onStart()
        if(mAuth.currentUser == null){
            startActivity(Intent(this,LoginActivity::class.java))
        }
        else{
            Toast.makeText(this,"Already signed In", Toast.LENGTH_LONG).show()
        }
    }
}