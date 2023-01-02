package com.example.roman.handgum.database.repositoryImpl

import com.example.roman.handgum.commonentity.db.repository.RevRepository
import com.example.roman.handgum.commonentity.ui.models.ReviewModel
import com.example.roman.handgum.database.AppDatabase
import com.example.roman.handgum.database.domain.mappers.ReviewMapper
import javax.inject.Inject

/**
 * @author rofor
 */
class RevRepositoryImpl @Inject constructor(revMapper: ReviewMapper, appDatabase: AppDatabase) :
    RevRepository {

    private val dao = appDatabase.revDao()
    private val mapper = revMapper

    override fun getAll(): List<ReviewModel> {
        return mapper.entityListToModelList(dao.getAll())
    }

    override fun loadById(id: String): ReviewModel {
        return mapper.entityToModel(dao.getReviewById(id))
    }

    override fun insert(revList: List<ReviewModel>) {
        dao.insertAll(mapper.modelListToEntityList(revList))
    }

    override fun deleteAll() {
        dao.deleteAll()
    }

}