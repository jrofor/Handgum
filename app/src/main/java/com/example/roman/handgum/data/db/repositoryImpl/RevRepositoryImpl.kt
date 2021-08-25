package com.example.roman.handgum.data.db.repositoryImpl

import com.example.roman.handgum.data.db.AppDatabase
import com.example.roman.handgum.data.db.repository.RevRepository
import com.example.roman.handgum.domain.mappers.ReviewMapper
import com.example.roman.handgum.domain.models.ReviewModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author rofor
 */
@Singleton
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