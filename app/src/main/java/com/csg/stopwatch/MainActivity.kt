package com.csg.stopwatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        viewModel.time.observe(this, Observer { time ->
            val sec = time / 100
            val milli = time % 100

            runOnUiThread {
                sec_text.text = "$sec"
                milli_sec_text.text = "$milli"
            }
        })

        button_start.setOnClickListener {
            viewModel.onStartButtonClicked()

        }
    }

}
