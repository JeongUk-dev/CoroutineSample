package com.jaydev.coroutinesample.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jaydev.coroutinesample.R
import com.jaydev.coroutinesample.scope.ScopeActivity
import com.jaydev.coroutinesample.databinding.ActivityMainBinding
import com.jaydev.coroutinesample.prevent_click.ClickActivity
import com.jaydev.coroutinesample.room.RoomActivity

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
			clickButton.setOnClickListener {
				startActivity(Intent(this@MainActivity, ClickActivity::class.java))
			}

			roomButton.setOnClickListener{
				startActivity(Intent(this@MainActivity, RoomActivity::class.java))
			}

			scopeButton.setOnClickListener{
				startActivity(Intent(this@MainActivity, ScopeActivity::class.java))
			}

		}
	}
}