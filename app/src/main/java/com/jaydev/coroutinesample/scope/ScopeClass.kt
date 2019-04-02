package com.jaydev.coroutinesample.scope

import android.content.Context
import com.jaydev.coroutinesample.room.Data
import com.jaydev.coroutinesample.room.DataBase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ScopeClass(private val context: Context) : CoroutineScope {

	private var job = Job()
	override val coroutineContext: CoroutineContext
		get() = Dispatchers.Main + job


	suspend fun generateDataWithInsert() = withContext(coroutineContext) {
		withContext(Dispatchers.IO) {
			val dao = DataBase.getInstance(context).dataDao()
			dao.insert(Data("Scope Sample", "Insert Scope Sample ${System.currentTimeMillis()}"))
		}
	}


	fun cancel() {
		job.cancel()
	}
}