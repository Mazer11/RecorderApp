package com.internship.recorderapp.data.local.room

import com.internship.recorderapp.data.dataclasses.StoredRecord
import kotlinx.coroutines.flow.Flow

class LocalDataRepository(private val roomDao: RoomDao) {

    suspend fun insertNewRecord(record: StoredRecord) {
        roomDao.insertNewRecord(record)
    }

    fun selectAll(): Flow<List<StoredRecord>> {
        return roomDao.selectAll()
    }

    suspend fun deleteRecord(path: String) {
        roomDao.deleteRecord(path)
    }

}