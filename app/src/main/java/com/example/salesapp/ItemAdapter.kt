package com.example.salesapp

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ItemAdapter(private val context: Context, private var list: ArrayList<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return ItemHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemHolder).bind(
            list[position].name,
            list[position].price,
            list[position].photo,
            list[position].id
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var itemName: TextView = itemView.findViewById(R.id.item_name)
        private var itemPrice: TextView = itemView.findViewById(R.id.item_price)
        private var itemPhoto: ImageView = itemView.findViewById(R.id.item_photo)
        private var itemAddPhoto: ImageView = itemView.findViewById(R.id.item_add_photo)

        fun bind(n: String, p: Double, u: String, item_id: Int) {
            itemName.text = n
            itemPrice.text = p.toString()
            val web = "http://192.168.1.119/SalesWeb/images/$u"
            web.replace(" ", "%20")
            Picasso.get().load(web).into(itemPhoto)

            itemAddPhoto.setOnClickListener {
                UserInfo.itemId = item_id
                val obj = QtyFragment()
                val manager = (itemView.context as Activity).fragmentManager
                obj.show(manager, "Qty")
            }
        }
    }
}