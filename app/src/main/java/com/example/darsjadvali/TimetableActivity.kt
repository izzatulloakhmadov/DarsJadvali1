package com.example.darsjadvali

import RetrofitClient
import TimetableResponse
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimetableActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timetable)

        val token = intent.getStringExtra("TOKEN") ?: ""
        val textView = findViewById<TextView>(R.id.timetableTextView)

        RetrofitClient.instance.getTimetable("Bearer $token").enqueue(object : Callback<TimetableResponse> {
            override fun onResponse(call: Call<TimetableResponse>, response: Response<TimetableResponse>) {
                if (response.isSuccessful) {
                    val timetable = response.body()?.timetable?.joinToString("\n") ?: "Ma'lumot yo‘q"
                    textView.text = timetable
                } else {
                    Toast.makeText(this@TimetableActivity, "Dars jadvali yuklanmadi", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TimetableResponse>, t: Throwable) {
                Toast.makeText(this@TimetableActivity, "Tarmoq xatosi", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
