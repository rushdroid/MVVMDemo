package com.rushabh.wipro_rushabh.roomDB

import androidx.room.*


@Dao
interface FactDao {

    @Query("SELECT * FROM FactTable")
    fun getAll(): List<Facts>

    @Insert
    fun insert(trendingRepo: Facts): Long

    @Insert
    fun insertAll(trendingRepo: List<Facts>): List<Long>

    @Delete
    fun delete(trendingRepo: Facts)

    @Query("DELETE from FactTable")
    fun deleteAll()

    @Update
    fun updateTodo(trendingRepo: Facts)
}