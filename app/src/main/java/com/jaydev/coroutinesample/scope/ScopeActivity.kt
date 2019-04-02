package com.jaydev.coroutinesample.scope

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jaydev.coroutinesample.R
import com.jaydev.coroutinesample.databinding.ActivityScopeBinding
import com.jaydev.coroutinesample.room.DataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScopeActivity:AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val work = ScopeClass(this)

		DataBindingUtil.setContentView<ActivityScopeBinding>(this, R.layout.activity_scope).apply {
			button.setOnClickListener {
				CoroutineScope(Dispatchers.Main).launch {
					work.generateDataWithInsert()

					val dao = DataBase.getInstance(this@ScopeActivity).dataDao()

					val string = withContext(Dispatchers.IO) {
						val list = dao.getDataList()

						val stringBuilder = StringBuilder()
						list.forEach { stringBuilder.appendln("${it.title}, ${it.description}") }

						stringBuilder.toString()
					}
					textView.text = string
				}
			}
		}
	}
}