package com.example.test.testcanvas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view9.setOnClickListener {
            startActivity(Intent(MainActivity@ this, TestShaderActivity::class.java))
        }

        view15.setOnClickListener {
            startActivity(Intent(MainActivity@ this, StaticLayoutActivity::class.java))
        }

        view16.setOnClickListener {
            startActivity(Intent(MainActivity@ this, BreakTextActivity::class.java))
        }
        view17.setOnClickListener {
            startActivity(Intent(MainActivity@ this, TestCamera1Activity::class.java))
        }

        view18.setOnClickListener {
            startActivity(Intent(MainActivity@ this, TestCamera2Activity::class.java))
        }
        view19.setOnClickListener {
            startActivity(Intent(MainActivity@ this, FlipboardAnimationActivity::class.java))
        }
    }
}