package com.example.salesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class OrderAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val url = "http://192.168.1.119/SalesWeb/get_temp.php?mobile=" + UserInfo.mobile
        val orderLv: ListView = findViewById(R.id.order_lv)

        val list = ArrayList<String>()
        val rq: RequestQueue = Volley.newRequestQueue(this)
        val jar = JsonArrayRequest(Request.Method.GET, url, null, { response ->
            for (x in 0 until response.length())
                list.add(
                    response.getJSONObject(x).getString("name") + "\n" +
                            "Ціна : " + response.getJSONObject(x).getString("price") + "\n" +
                            "Кількість : " + response.getJSONObject(x).getString("qty")
                )

            val adp = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
            orderLv.adapter = adp
        }, { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        })
        rq.add(jar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.item_menu) {
            val i = Intent(this, HomeAct::class.java)
            startActivity(i)
        }

        if (item.itemId == R.id.item_cancel) {
            val url = "http://192.168.1.119/SalesWeb/cancel_order.php?mobile=" + UserInfo.mobile

            val rq: RequestQueue = Volley.newRequestQueue(this)
            val sr = StringRequest(Request.Method.GET, url, {
                val i = Intent(this, HomeAct::class.java)
                startActivity(i)
            }, { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            })
            rq.add(sr)
        }

        if (item.itemId == R.id.item_confirm) {
            val url = "http://192.168.1.119/SalesWeb/confirm_order.php?mobile=" + UserInfo.mobile

            val rq: RequestQueue = Volley.newRequestQueue(this)
            val sr = StringRequest(Request.Method.GET, url, { response ->
                val i = Intent(this, TotalAct::class.java)
                i.putExtra("bno", response)
                startActivity(i)
            }, { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            })
            rq.add(sr)
        }

        return super.onOptionsItemSelected(item)
    }

}