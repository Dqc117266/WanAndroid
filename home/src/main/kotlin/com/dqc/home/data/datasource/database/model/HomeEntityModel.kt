package com.dqc.home.data.datasource.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "articles")
@TypeConverters(

)

internal data class HomeEntityModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

)