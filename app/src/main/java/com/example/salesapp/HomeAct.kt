package com.example.salesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.salesapp.databinding.ActivityHomeBinding

class HomeAct : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = "http://192.168.1.119/SalesWeb/get_cat.php"
        val list = ArrayList<String>()
        val rq: RequestQueue = Volley.newRequestQueue(this)
        val jar = JsonArrayRequest(Request.Method.GET, url, null, { response ->
            for (x in 0 until response.length())
                list.add(response.getJSONObject(x).getString("category"))

            val adp = ArrayAdapter(this, R.layout.my_textview, list)
            binding.homeCat.adapter = adp
        }, { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        })
        rq.add(jar)

        binding.homeCat.setOnItemClickListener { _, _, i, _ ->
            val cat: String = list[i]
            val obj = Intent(this, ItemAct::class.java)
            obj.putExtra("cat", cat)
            startActivity(obj)
        }
    }
}