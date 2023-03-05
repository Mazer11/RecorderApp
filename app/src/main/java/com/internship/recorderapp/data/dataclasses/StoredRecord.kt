package com.internship.recorderapp.data.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stored_records")
data class StoredRecord(
    @PrimaryKey val path: String,
     val name: String? = null
)
