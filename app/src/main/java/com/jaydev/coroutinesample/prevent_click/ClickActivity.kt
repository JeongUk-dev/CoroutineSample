package com.jaydev.coroutinesample.prevent_click

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jaydev.coroutinesample.R
import com.jaydev.coroutinesample.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class ClickActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

		binding.button.setOnClickListener(PreventClickListener(View.OnClickListener {
            count++
            binding.textView.text = "${binding.textView.text}\nNow Click $count Countdown"
        }))

        binding.button.setOnPreventClickListener {
            count++
            binding.textView.text = ""
            for (i in 10 downTo 1) {
                delay(500)
                binding.textView.text = "${binding.textView.text}\nNow Click $count Countdown $i ..."
            }
        }
    }
}
