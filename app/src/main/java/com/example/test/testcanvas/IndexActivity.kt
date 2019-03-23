package com.example.test.testcanvas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_index.*

class IndexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_index)

        view9.setOnClickListener {
            startActivity(Intent(MainActivity@ this, BreakTextActivity::class.java))
        }
        view10.setOnClickListener {
            startActivity(Intent(MainActivity@ this, FlipboardAnimationActivity::class.java))
        }
    }
}
