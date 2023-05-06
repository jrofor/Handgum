package com.example.roman.handgum.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roman.handgum.database.entity.ReviewEntity

/**
 * @author rofor
 */
@Dao
interface RevDao {

    @Query("SELECT * FROM Review")
    fun getAll(): List<ReviewEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(reviewsEntity: List<ReviewEntity>)

    @Query("SELECT * FROM Review WHERE url = :url")
    fun getReviewByUrl(url: String): ReviewEntity

    @Query("DELETE FROM Review")
    fun deleteAll()
}