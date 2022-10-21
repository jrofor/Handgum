package com.example.roman.handgum.database.repository

import com.example.roman.handgum.database.domain.models.ReviewModel

/**
 * @author rofor
 */
interface RevRepository {

    fun getAll(): List<ReviewModel>

    fun loadById(id: String): ReviewModel

    fun insert(revList: List<ReviewModel>)

    fun deleteAll()
}