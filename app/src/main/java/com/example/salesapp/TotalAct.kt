package com.example.salesapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class TotalAct : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total)

        val url =
            "http://192.168.1.119/SalesWeb/get_total.php?bill_no=" + intent.getStringExtra("bno")

        val totalTv: TextView = findViewById(R.id.total_tv)

        val rq: RequestQueue = Volley.newRequestQueue(this)
        val sr = StringRequest(Request.Method.GET, url, { response ->
            totalTv.text = "Загальна сума: $response грн"
        }, { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        })
        rq.add(sr)

        val btnBack: Button = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            val i = Intent(this, HomeAct::class.java)
            startActivity(i)
        }
    }
}