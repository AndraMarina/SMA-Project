package com.example.foodshare.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*
import android.net.Uri
import com.example.foodshare.R
import com.example.foodshare.order.Order
import com.example.foodshare.order.OrderAdapter


class RestaurantActivity : AppCompatActivity() {

    private lateinit var ref : DatabaseReference
    lateinit var orderList : MutableList<Order>
    lateinit var listView : ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        orderList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("orders")
        listView = findViewById<ListView>(R.id.list_item)


        ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                return
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    for(or in p0.children){

                        val order = or.getValue(Order::class.java)
                        orderList.add(order!!)
                    }

                    val adapter = OrderAdapter(
                        applicationContext,
                        R.layout.orders,
                        orderList
                    ) { order: Order -> contactOrder(order) }
                    listView.adapter = adapter
                }
            }

        })
    }

    private fun contactOrder(order: Order){

        val intentCall = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + order.phone))
        startActivity(intentCall)

    }
}
