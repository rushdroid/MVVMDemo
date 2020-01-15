package com.rushabh.wipro_rushabh.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rushabh.wipro_rushabh.R
import kotlinx.android.synthetic.main.activity_error.*


class ErrorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
        init()
    }

    fun init() {
        btnRetry.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {

    }
}
