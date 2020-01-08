package com.example.foodshare.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.foodshare.R
import com.example.foodshare.order.Order
import com.example.foodshare.order.OrderAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ShelterActivity : AppCompatActivity() {


    private lateinit var ref : DatabaseReference
    private lateinit var orderList : MutableList<Order>
    private lateinit var listView : ListView
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelter)

        orderList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("orders")
        listView = findViewById(R.id.list_item_shelter)
        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                return
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()){
                    for(or in p0.children){

                        val order = or.getValue(Order::class.java)
                        if (currentUser?.email.toString() == order?.email)
                            orderList.add(order!!)
                    }

                    val adapter = OrderAdapter(
                        applicationContext,
                        R.layout.orders_shelter,
                        orderList
                    ) { order: Order -> deleteOrder(order) }
                    listView.adapter = adapter
                }
            }

        })
    }

    private fun deleteOrder(order: Order){

        val ref = FirebaseDatabase.getInstance().getReference("orders")
        ref.child(order.id).removeValue()
        Toast.makeText(this, "Your Request Has Been honoured", Toast.LENGTH_LONG).show()
        finish()
        overridePendingTransition(0,0)
        startActivity(intent)
        overridePendingTransition(0,0)
    }
}
