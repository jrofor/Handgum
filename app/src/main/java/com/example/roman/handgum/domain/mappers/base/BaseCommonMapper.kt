package com.example.roman.handgum.domain.mappers.base

/**
 * @author rofor
 */
interface BaseCommonMapper<Api, Entity, Model> {
    fun responseToModel(api: Api): Model
    fun responseListToModelList(listApi: List<Api>): List<Model>
    fun modelToEntity(model: Model): Entity
    fun modelListToEntityList(modelList: List<Model>): List<Entity>
    fun entityToModel(entity: Entity): Model
    fun entityListToModelList(entityList: List<Entity>): List<Model>

}