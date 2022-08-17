package com.example.salesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.salesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginSignup.setOnClickListener {
            val i = Intent(this, RegAct::class.java)
            startActivity(i)
        }

        binding.loginButton.setOnClickListener {
            val url =
                "http://192.168.1.119/SalesWeb/login.php?mobile=" + binding.loginMobile.text.toString() + "&password=" +
                        binding.loginPassword.text.toString()

            val rq: RequestQueue = Volley.newRequestQueue(this)
            val sr = StringRequest(Request.Method.GET, url, { response ->
                if (response.equals("0"))
                    Toast.makeText(this, "Некоректні дані", Toast.LENGTH_LONG).show()
                else {
                    UserInfo.mobile = binding.loginMobile.text.toString()

                    val i = Intent(this, HomeAct::class.java)
                    startActivity(i)
                }

            }, { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            })

            rq.add(sr)
        }
    }
}