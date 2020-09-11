package com.internshala.emailauthdemo

import android.content.Intent
import android.os.Build.VERSION_CODES.M
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Timeline : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth

    lateinit var mDatabase : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference("Name")
        val disptext = findViewById<View>(R.id.disptxt) as TextView


        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               val result = snapshot.child("Name").toString()
                disptext.text = "Welcome" + result
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu,menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item!!.itemId == R.id.signout){
//            mAuth.signOut()
//            startActivity(Intent(this, LoginActivity::class.java))
//            Toast.makeText(this, " succesfully loged out", Toast.LENGTH_LONG).show()
//        }
//        return super.onOptionsItemSelected(item)
//    }
}