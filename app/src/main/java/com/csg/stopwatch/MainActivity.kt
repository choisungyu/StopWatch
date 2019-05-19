package com.csg.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var time = 0
    private var timerTask: Timer? = null
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_start.setOnClickListener {
            isRunning = !isRunning
            if (isRunning) {
                start()
            } else {
                pause()
            }

        }
    }

    override fun onDestroy() {
        pause()
        super.onDestroy()
    }

    private fun pause() {
        // null 이 아니라면 cancel
        timerTask?.cancel()
    }

    private fun start() {
        timerTask = timer(period = 10) {
            time++
            val sec = time / 100
            val milli = time % 100

            runOnUiThread {
                sec_text.text = "$sec"
                milli_sec_text.text = "$milli"
            }
        }
    }
}
