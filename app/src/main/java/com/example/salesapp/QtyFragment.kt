package com.example.salesapp

import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class QtyFragment : DialogFragment() {

    @Deprecated("Deprecated in Java")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_qty, container, false)

        val et = v.findViewById<EditText>(R.id.et_qty)
        val btn = v.findViewById<Button>(R.id.btn_qty)

        btn.setOnClickListener {
            val url = "http://192.168.1.119/SalesWeb/add_temp.php?mobile=" + UserInfo.mobile +
                    "&itemid=" + UserInfo.itemId + "&qty=" + et.text.toString()

            val rq: RequestQueue = Volley.newRequestQueue(activity)
            val sr = StringRequest(Request.Method.GET, url, {
                val i = Intent(activity, OrderAct::class.java)
                startActivity(i)
            }, { error ->
                Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
            })
            rq.add(sr)
        }

        return v
    }

}