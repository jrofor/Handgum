package com.example.roman.handgum.commonentity.db.repository

import com.example.roman.handgum.commonentity.ui.models.ReviewModel

/**
 * @author rofor
 */
interface RevRepository {

    fun getAll(): List<ReviewModel>

    fun loadById(id: String): ReviewModel

    fun insert(revList: List<ReviewModel>)

    fun deleteAll()
}