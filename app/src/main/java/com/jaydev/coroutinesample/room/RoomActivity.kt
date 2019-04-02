package com.jaydev.coroutinesample.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jaydev.coroutinesample.R
import com.jaydev.coroutinesample.databinding.ActivityRoomBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		DataBindingUtil.setContentView<ActivityRoomBinding>(this, R.layout.activity_room).apply {


			insertButton.setOnClickListener {
				val database = DataBase.getInstance(this@RoomActivity).dataDao()

				CoroutineScope(Dispatchers.IO).launch {
					val data = Data("insert", "insert data ${System.currentTimeMillis()}")
					database.insert(data)
				}
			}


			refreshButton.setOnClickListener {

				val database = DataBase.getInstance(this@RoomActivity).dataDao()

				CoroutineScope(Dispatchers.Main).launch {

					val list = CoroutineScope(Dispatchers.IO).async {
						database.getDataList()
					}.await()


					val stringBuilder = StringBuilder()
					list.forEach { stringBuilder.appendln("${it.title}, ${it.description}") }

					textView.text = stringBuilder.toString()
				}


			}
		}
	}
}