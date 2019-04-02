package com.jaydev.coroutinesample.scope

import android.content.Context
import com.jaydev.coroutinesample.room.Data
import com.jaydev.coroutinesample.room.DataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ScopeClass(private val context: Context) : CoroutineScope {

	private var job = Job()
	override val coroutineContext: CoroutineContext
		get() = Dispatchers.Main + job


	suspend fun generateDataWithInsert() {
		withContext(Dispatchers.IO) {
			val dao = DataBase.getInstance(context).dataDao()
			dao.insert(Data("Scope Sample", "Insert Scope Sample ${System.currentTimeMillis()}"))
		}
	}

	suspend fun getDataList(): List<Data> = withContext(coroutineContext) {
		val list: List<Data>

		val dao = DataBase.getInstance(context).dataDao()
		list = withContext(Dispatchers.IO) {
			dao.getDataList()
		}
		list
	}

	fun cancel() {
		job.cancel()
	}
}