package com.example.lintunggalih

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val etUsername = findViewById<EditText>(R.id.et_username)
        val btnLogin = findViewById<Button>(R.id.btn_login)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.clear()
        editor.apply()
        btnLogin.setOnClickListener{
            val username = etUsername.text.toString()

            if(username.isNotEmpty()){
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.apply()
            }
            val intent = Intent(this, ProfilPasien::class.java)
            startActivity(intent)
        }
    }
}