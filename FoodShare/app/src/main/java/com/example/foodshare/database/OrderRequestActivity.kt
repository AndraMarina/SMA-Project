package com.example.foodshare.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.foodshare.R
import com.example.foodshare.order.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_order_request.*


class OrderRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_request)

        val submitButton: Button = findViewById<Button>(R.id.submitRequestButton)
        submitButton.setOnClickListener {
            saveOrder()
        }

        val viewButton: Button = findViewById<Button>(R.id.viewRequestButton)
        viewButton.setOnClickListener{ var changeViewIntent = Intent(this,
            ShelterActivity::class.java)
            startActivity(changeViewIntent)

        }

    }

    private fun saveOrder(){
        var auth: FirebaseAuth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser


        val name = fieldShelter.text.toString()
        val address = fieldAddress.text.toString()
        val portionNo = fieldPortions.text.toString()
        val mealType = fieldMeal.text.toString()
        val phoneNo = fieldPhone.text.toString()
        val email = currentUser?.email.toString()

        if(name.isEmpty()){
            fieldShelter.error = "Please enter a name"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("orders")

        val orderId = ref.push().key.toString()
        val order = Order(
            orderId,
            name,
            address,
            phoneNo,
            mealType,
            portionNo,
            email
        )

        ref.child(orderId).setValue(order).addOnCompleteListener{
            Toast.makeText(applicationContext, "You request has been saved", Toast.LENGTH_LONG).show()
        }

    }

}
