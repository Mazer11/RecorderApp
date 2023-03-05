package com.internship.recorderapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.internship.recorderapp.data.dataclasses.StoredRecord

@Database(
    entities = [StoredRecord::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    abstract val roomDao: RoomDao

    companion object {
        const val DATABASE_NAME = "recommendaily_db"

    }
}