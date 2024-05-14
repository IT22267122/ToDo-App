package com.example.taskmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, MainActivity::class.java)

        // Optionally, you can add extra data to the intent
        intent.putExtra("key", "value")

        Handler().postDelayed({
            // Starting MainActivity after 3 seconds
            startActivity(intent)
            // Finish the current activity to prevent going back to it when pressing back button
            finish()
        }, 3000) // 3000 milliseconds = 3 seconds

    }
}