package com.example.salesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.salesapp.databinding.ActivityRegBinding

class RegAct : AppCompatActivity() {

    private lateinit var binding: ActivityRegBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regSignup.setOnClickListener {
            if (binding.regPassword.text.toString() == binding.regConfirm.text.toString()) {
                val url =
                    "http://192.168.1.119/SalesWeb/add_user.php?mobile=" + binding.regMobile.text.toString() + "&password=" +
                            binding.regPassword.text.toString() + "&name=" + binding.regName.text.toString() + "&address=" + binding.regAddress.text.toString()

                val rq: RequestQueue = Volley.newRequestQueue(this)
                val sr = StringRequest(Request.Method.GET, url, { response ->
                    if (response.equals("0"))
                        Toast.makeText(this, "Такий номер вже існує", Toast.LENGTH_LONG).show()
                    else {
                        Toast.makeText(this, "Аккаунт створений", Toast.LENGTH_LONG).show()
                        UserInfo.mobile = binding.regMobile.text.toString()
                        val i = Intent(this, HomeAct::class.java)
                        startActivity(i)
                    }

                }, { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                })

                rq.add(sr)

            } else {
                Toast.makeText(this, "Пароль не співпадає", Toast.LENGTH_LONG).show()
            }
        }
    }
}