package com.rushabh.wipro_rushabh.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rushabh.wipro_rushabh.R
import com.rushabh.wipro_rushabh.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init() {

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getFacts().observe(this, Observer {
            print(it)
        })

        viewModel.isLoading().observe(this, Observer {
            if (it) {

            } else {

            }
        })

        viewModel.isInternetAvailable().observe(this, Observer {
            if (it) {
                startActivity(Intent(this, ErrorActivity::class.java))
            }
        })
        swipe_to_refresh_layout.setOnRefreshListener {
            viewModel.getFactsFromAPI()
        }
        viewModel.getFactsFromAPI()
    }


}
