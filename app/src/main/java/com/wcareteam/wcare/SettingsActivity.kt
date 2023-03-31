package com.wcareteam.wcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onBackPressed() {
        val navIntent = Intent(this, NavActivity::class.java)
        startActivity(navIntent)
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        findViewById<TextView>(R.id.idtext).text = currentUser?.uid
        findViewById<TextView>(R.id.nametext).text = currentUser?.displayName
        findViewById<TextView>(R.id.emailtext).text = currentUser?.email

        findViewById<Button>(R.id.signOutBtn).setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        findViewById<ImageButton>(R.id.backbtn).setOnClickListener {
            startActivity(Intent(this, NavActivity::class.java))
            finish()
        }
    }
}