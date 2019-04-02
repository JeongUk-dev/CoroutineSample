package com.jaydev.coroutinesample.room

import androidx.room.*


@Entity(tableName = "dataTable")
data class Data(val title: String, val description: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Dao
interface DataDao {
    @Query("SELECT * FROM dataTable WHERE userId = :userId")
    fun getReadNotices(userId: String): List<ReadNotice>

    @Query("SELECT * FROM dataTable WHERE userId = :userId AND noticeId = :noticeId")
    fun getReadNotice(userId: String, noticeId: Int): ReadNotice?

    @Query("UPDATE dataTable SET readState = :readState WHERE noticeId = :noticeId AND userId = :userId")
    fun updateReadNotice(userId: String, noticeId: Int, readState: Boolean)

    @Query("SELECT dataTable FROM readNotice WHERE userId = :userId AND noticeId = :noticeId")
    fun isRead(userId: String, noticeId: Int): Boolean?

    @Insert
    fun insertAll(noticeList: List<ReadNotice>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notice: ReadNotice): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(notice: ReadNotice)

    @Query("DELETE FROM readNotice WHERE userId = :userId")
    fun clear(userId: String)
}