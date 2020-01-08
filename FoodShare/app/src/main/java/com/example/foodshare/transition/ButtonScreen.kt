package com.example.foodshare.transition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.foodshare.R
import com.example.foodshare.database.OrderRequestActivity
import com.example.foodshare.database.RestaurantActivity

class ButtonScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_screen)
        val sheltButton: Button = findViewById<Button>(R.id.ShelterButton)
        val restButton: Button = findViewById<Button>(R.id.RestaurantButton)


        sheltButton.setOnClickListener {
            var shelterIntent = Intent(this, OrderRequestActivity::class.java)
            startActivity(shelterIntent)

        }


        restButton.setOnClickListener {
            var restaurantIntent = Intent(this, RestaurantActivity::class.java)
            startActivity(restaurantIntent)

        }
    }
}
