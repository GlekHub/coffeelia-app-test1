package com.example.salesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.salesapp.databinding.ActivityItemBinding

class ItemAct : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cat: String? = intent.getStringExtra("cat")
        val url = "http://192.168.1.119/SalesWeb/get_items.php?category=$cat"
        val list = ArrayList<Item>()

        val rq: RequestQueue = Volley.newRequestQueue(this)
        val jar = JsonArrayRequest(Request.Method.GET, url, null, { response ->
            for (x in 0 until response.length())
                list.add(
                    Item(
                        response.getJSONObject(x).getInt("id"),
                        response.getJSONObject(x).getString("name"),
                        response.getJSONObject(x).getDouble("price"),
                        response.getJSONObject(x).getString("photo")
                    )
                )

            val adp = ItemAdapter(this, list)
            binding.itemRv.layoutManager = LinearLayoutManager(this)
            binding.itemRv.adapter = adp
        }, { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        })
        rq.add(jar)
    }
}