package com.jydev.decimaltext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val et = findViewById<CoinRemittanceEditText>(R.id.et).apply {
            coinAmount.observe(this@MainActivity){
                println("값은:$it")
            }
        }
        findViewById<Button>(R.id.plus_button).setOnClickListener {
            et.plusNumber(100)
        }
    }
}