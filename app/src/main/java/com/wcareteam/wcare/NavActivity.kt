package com.wcareteam.wcare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.wcareteam.wcare.databinding.ActivityNavBinding

class NavActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNavBinding

          override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        binding = ActivityNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragement(Home())
        binding.bottomNavigationView.selectedItemId = R.id.home
        binding.bottomNavigationView.setOnItemSelectedListener {

            val openSettings = Intent(this, SettingsActivity::class.java)

            when(it.itemId){
                R.id.home -> replaceFragement(Home())
                R.id.contacts -> replaceFragement(Contacts())
                R.id.settings -> {
                    startActivity(openSettings)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                }

                else ->{
                }
            }
            true
        }

    }

    private fun  replaceFragement(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }




}