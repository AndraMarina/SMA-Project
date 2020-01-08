package com.example.foodshare.transition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AlphaAnimation
import android.widget.TextView
import com.example.foodshare.R
import com.example.foodshare.authentification.AutenthificationActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById<TextView>(R.id.app_name)

        setUpFadeAnimation(textView)

        textView.setOnClickListener { val changeViewIntent = Intent(this,
            AutenthificationActivity::class.java)
            startActivity(changeViewIntent)
        }
    }

    private fun setUpFadeAnimation(textView: TextView) {
        // Start from 0.1f if you desire 90% fade animation
        val fadeIn = AlphaAnimation(0.0f, 1.0f)
        fadeIn.duration = 1000
        fadeIn.startOffset = 3000
        // End to 0.1f if you desire 90% fade animation
        val fadeOut = AlphaAnimation(1.0f, 0.0f)
        fadeOut.duration = 1000
        fadeOut.startOffset = 3000

        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                // start fadeOut when fadeIn ends (continue)
                textView.startAnimation(fadeOut)
            }

            override fun onAnimationRepeat(arg0: Animation) {}

            override fun onAnimationStart(arg0: Animation) {}
        })

        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                // start fadeIn when fadeOut ends (repeat)
                textView.startAnimation(fadeIn)
            }

            override fun onAnimationRepeat(arg0: Animation) {}

            override fun onAnimationStart(arg0: Animation) {}
        })

        textView.startAnimation(fadeOut)
    }

}
