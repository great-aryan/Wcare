package com.wcareteam.wcare

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private  lateinit var mAuth : FirebaseAuth
    lateinit var tag1: TextView
    var v = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        tag1 = findViewById(R.id.tag1)

        tag1.translationY = 80f

        tag1.alpha = v

        tag1.animate().translationY(0f).alpha(1f).setDuration(500).setStartDelay(0).start()

        Handler().postDelayed({
            if (user !=null) {
                val dashboardIntent = Intent(this, NavActivity::class.java)
                startActivity(dashboardIntent)
                finish()
            }
            else{
                val signInIntent = Intent(this, LoginActivity::class.java)
                startActivity(signInIntent)
                finish()
            }

        }, 1600)
    }
}