package com.jaydev.coroutinesample.room

import android.content.Context
import androidx.room.*


@Entity(tableName = "dataTable")
data class Data(val title: String, val description: String) {
	@PrimaryKey(autoGenerate = true)
	var id: Long = 0
}

@Dao
interface DataDao {
	@Query("SELECT * FROM dataTable")
	fun getDataList(): List<Data>

	@Insert
	fun insertAll(List: List<Data>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(data: Data)

	@Query("DELETE FROM dataTable")
	fun clear()
}


@Database(entities = [Data::class], version = 1)
abstract class DataBase : RoomDatabase() {

	abstract fun dataDao(): DataDao


	companion object {
		private var INSTANCE: DataBase? = null

		private val lock = Any()

		fun getInstance(context: Context): DataBase {
			synchronized(lock) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(context.applicationContext, DataBase::class.java, "Data.db").build()
				}
				return INSTANCE!!
			}
		}
	}
}