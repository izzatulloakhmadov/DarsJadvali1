package com.example.darsjadvali

import LoginRequest
import LoginResponse
import RetrofitClient
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            if (user.isNotEmpty() && pass.isNotEmpty()) {
                login(user, pass)
            } else {
                Toast.makeText(this, "Iltimos, login va parolni kiriting", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(user: String, pass: String) {
        val request = LoginRequest(user, pass)
        RetrofitClient.instance.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token ?: ""
                    val intent = Intent(this@LoginActivity, TimetableActivity::class.java)
                    intent.putExtra("TOKEN", token)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Login xato", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Tarmoq xatosi", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
