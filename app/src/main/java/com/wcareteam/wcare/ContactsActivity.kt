package com.wcareteam.wcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ContactsActivity : AppCompatActivity() {
    override fun onBackPressed() {
        val navIntent = Intent(this, NavActivity::class.java)
        startActivity(navIntent)
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
    }

}