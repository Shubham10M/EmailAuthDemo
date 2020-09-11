package com.internshala.emailauthdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mAuth = FirebaseAuth.getInstance()
        val btnregister = findViewById<View>(R.id.btnregister) as Button
        mDatabase = FirebaseDatabase.getInstance().getReference("Name")



        btnregister.setOnClickListener(View.OnClickListener {
                view -> register()
        })
    }
    private fun register(){
//        val Name = findViewById<View>(R.id.Name) as EditText
//        val edtEmail = findViewById<View>(R.id.edtEmail) as EditText
//        val edtpass = findViewById<View>(R.id.edtpass) as EditText

        var passward = edtpass.text.toString()
        var email =  edtEmail.text.toString()
        var name = Name.text.toString()
        if(!email.isEmpty() && !passward.isEmpty() && !name.isEmpty()){
            mAuth/*.signInWithEmailAndPassword(email, passward)*/
                .createUserWithEmailAndPassword(email, passward)
                .addOnSuccessListener {
                    // account creation successful, upate the UI and send an account verification email
                    Log.e("TAG","email is:----------------------------" +it.user?.email)
                    Toast.makeText(this, "User created successfully", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Log.e("TAG","error is:---------------------------- "+it.message );
                    // account creation failed, probably the account already exists or bad network connection
                    it.printStackTrace()
                    Toast.makeText(this, "Error while creating user", Toast.LENGTH_LONG).show()
                }

        }
        else{
            Toast.makeText(this, " Please fill up the credentials", Toast.LENGTH_LONG).show()
        }
    }
}