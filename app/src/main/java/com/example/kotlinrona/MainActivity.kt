package com.example.kotlinrona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var ronaArray = ArrayList<ImageView>()

    var score: Int = 0

    var handler: Handler = Handler()
    var runnable: Runnable = Runnable { }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        score = 0

        ronaArray = arrayListOf(
            imageView1,
            imageView2,
            imageView3,
            imageView4,
            imageView5,
            imageView6,
            imageView7,
            imageView8,
            imageView9
        )

        hide()

        object : CountDownTimer(20000, 1000) {
            override fun onTick(p0: Long) {
                textTime.text = "Time Left: " + p0 / 1000
            }

            override fun onFinish() {
                textTime.text = "Time's up!"

                handler.removeCallbacks(runnable)

                for (image in ronaArray) {
                    image.visibility = View.INVISIBLE
                }
            }

        }.start()

    }

    fun hide() {

        runnable = object : Runnable {
            override fun run() {

                for (image in ronaArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = java.util.Random()
                val index = random.nextInt(8 - 0)
                ronaArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)
            }

        }

        handler.post(runnable)
    }

    fun score(view: View) {
            score++
            textScore.text = "Score: " + score
        }

}