package com.wcareteam.wcare

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView


class Contacts : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1001
        private const val REQUEST_SMS_PERMISSION = 1002
    }

    private val CALL_PERMISSION_REQUEST_CODE = 1
    private fun makePhoneCall(phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.CALL_PHONE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CALL_PHONE),
                CALL_PERMISSION_REQUEST_CODE
            )
        } else {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)
        }
    }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
            if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    // try to make the phone call again
                    makePhoneCall("1234567890")
                } else {
                    // permission denied
                    // show a message to the user
                    Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contacts, container, false)


        val contactbutton1 = view.findViewById<Button>(R.id.contact_button1)
//        val contactbutton2 = view.findViewById<Button>(R.id.contact_button2)
//        val contactbutton3 = view.findViewById<Button>(R.id.contact_button3)
//        val contactbutton4 = view.findViewById<Button>(R.id.contact_button4)
//        val contactbutton5 = view.findViewById<Button>(R.id.contact_button5)


        contactbutton1.setOnClickListener{
            Log.d("MyApp", "Button clicked!")
            makePhoneCall(getString(R.string.Contact_number1))

        }
//        contactbutton2.setOnClickListener{ makePhoneCall(getString(R.string.Contact_number2)) }
//        contactbutton3.setOnClickListener{ makePhoneCall(getString(R.string.Contact_number3)) }
//        contactbutton4.setOnClickListener{ makePhoneCall(getString(R.string.Contact_number4)) }
//        contactbutton5.setOnClickListener{ makePhoneCall(getString(R.string.Contact_number5)) }


        return view


    }

}