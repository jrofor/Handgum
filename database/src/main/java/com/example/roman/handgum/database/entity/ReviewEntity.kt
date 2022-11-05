package com.example.roman.handgum.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * connected classes
 * [com.example.roman.handgum.commonentity.network.response.MovieRevResponse]
 * [com.example.roman.handgum.commonentity.ui.models.ReviewModel]
 * @author rofor
 */
@Entity(tableName = "Review")
class ReviewEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L

    @ColumnInfo(name = "displayTitle")
    var displayTitle: String = ""

    @ColumnInfo(name = "mpaaRating")
    var mpaaRating: String = ""

    @ColumnInfo(name = "publicationDate")
    var publicationDate: String = ""

    @ColumnInfo(name = "headline")
    var headline: String = ""

    @ColumnInfo(name = "summaryShort")
    var summaryShort: String = ""

    @ColumnInfo(name = "url")
    var url: String = ""

    @ColumnInfo(name = "src")
    var src: String = ""

    @ColumnInfo(name = "width")
    var width: String = ""

    @ColumnInfo(name = "height")
    var height: String = ""
}