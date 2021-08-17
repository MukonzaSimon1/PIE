package com.modcom.simon.pie
//MainActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {
    //declaring our views
    private lateinit var etUsername:EditText
    private lateinit var etPassword:EditText
    private lateinit var btnSignIn:ExtendedFloatingActionButton
    private val sharedPrefsFile = "mynameprefs"
    private lateinit var  sharedPrefences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPAssword)
        btnSignIn = findViewById(R.id.btnSignIn)

        sharedPrefences = getSharedPreferences(sharedPrefsFile,Context.MODE_PRIVATE)


        btnSignIn.setOnClickListener {
            //grabbing text from edittexts
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
//            Toast.makeText(this,"hELO", Toast.LENGTH_LONG).show()
//            Toast.makeText(this, "Username: $username\nPassword: $password", Toast.LENGTH_SHORT)
//                .show()


            //SAVE TO SHARED PRFERENCES
            val editor:SharedPreferences.Editor = sharedPrefences.edit()
            editor.putString("username",username)
            editor.apply()
            val homeActivity = Intent(Intent(this,HomeActivity::class.java))
            homeActivity.putExtra("username",username)
            startActivity(homeActivity)
            finish()


        }

    }


    override fun onStart() {
        super.onStart()
        val excistingusername = sharedPrefences.getString("username","default")
        if (!excistingusername .equals("default")){
            val homeActivity = Intent(Intent(this,HomeActivity::class.java))
            homeActivity.putExtra("username",excistingusername)
            startActivity(homeActivity)
            finish()
        }
    }
}



