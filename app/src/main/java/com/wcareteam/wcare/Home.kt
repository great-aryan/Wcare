package com.wcareteam.wcare

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView


class Home : Fragment() {
    private var switchOn: Boolean = false
    var isButtonClicked = false


      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        fun animateText(textView: TextView, text: String) {
            textView.text = ""
            val words = text.split(" ") // Split the text into words
            var i = 0 // Initialize the index of the current word to animate
            val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                                        val endIndex = (i + 2).coerceAtMost(words.size) // End index of the current batch of words
                    val word = words.subList(i, endIndex).joinToString(" ") // Join the current batch of words into a single string
                    textView.text = textView.text.toString() + " " + word
                    i = endIndex
                    if (i < words.size) {
                        handler.postDelayed(this, 100) // Delay between each word animation
                    }
                }
            }, 100) // Initial delay before starting the animation
        }


        // Find the button in the layout
        val lottieswitch = view.findViewById<LottieAnimationView>(R.id.lottieSwitch)
        val lottieswitchOff = view.findViewById<LinearLayout>(R.id.lottieSwitchOff)
        val consoletext= view.findViewById<TextView>(R.id.console_text)
        val hinttext= view.findViewById<TextView>(R.id.hintText)
        val newBackground = resources.getDrawable(R.drawable.round_bg)
        val clickAnimation = AnimationUtils.loadAnimation(context, R.anim.button_click_animation)
        val textToType =  resources.getString(R.string.console_text)
        val textToType2 =  resources.getString(R.string.console_text2)
        val handler = Handler(Looper.getMainLooper())

        lottieswitch.alpha = 0.0F

        // Set the OnClickListener for the button
        lottieswitch.setOnClickListener {

            if (!isButtonClicked) {
                lottieswitchOff.startAnimation(clickAnimation)
                consoletext.visibility = View.VISIBLE
                hinttext.visibility = View.INVISIBLE
                var i = 0
                lottieswitch.isEnabled = false // Disable the button

                lottieswitch.postDelayed({
                        lottieswitch.isEnabled = true
                    },2000)

                    lottieswitch.postDelayed({
                    lottieswitch.alpha = 1.0F
                    lottieswitch.background = newBackground
                    lottieswitch.playAnimation()

                    switchOn = false
                    isButtonClicked = true
                },300)
                animateText(consoletext, textToType)


            } else {
                if (switchOn) {
                    lottieswitchOff.startAnimation(clickAnimation)
                    lottieswitch.isEnabled = false // Disable the button

                    lottieswitch.postDelayed({
                        lottieswitch.isEnabled = true
                    },2000)
                    lottieswitch.alpha = 1.0F
                    lottieswitch.background = newBackground
                    lottieswitch.playAnimation()
                    switchOn = false
                    var i = 0
                    animateText(consoletext, textToType2)
                } else {
                    lottieswitchOff.startAnimation(clickAnimation)
                    lottieswitch.setAnimation(R.raw.warning3)
                    lottieswitch.playAnimation()
                    lottieswitch.alpha = 0.0F
                    var i = 0
                    consoletext.text = "Press Again for Re-Alert"
                    lottieswitch.setBackgroundResource(R.drawable.warn13)
                    switchOn = true
                }
            }
        }
        return view
    }
}