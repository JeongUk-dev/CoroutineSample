package com.jaydev.coroutinesample.prevent_click

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jaydev.coroutinesample.R
import com.jaydev.coroutinesample.databinding.ActivityClickBinding
import kotlinx.coroutines.delay

class ClickActivity : AppCompatActivity() {

    private var preventClickCount = 0
    private var coroutineClickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityClickBinding>(this, R.layout.activity_click).apply {

            preventButton.setOnClickListener(PreventClickListener(View.OnClickListener {
                preventClickCount++
                textView.text = "${textView.text}\nNow Click $preventClickCount"
            }))

            coroutineButton.setOnPreventClickListener {
                coroutineClickCount++
                textView.text = "${textView.text}\nNow Click $coroutineClickCount"
                delay(3000)
            }

        }
    }
}
