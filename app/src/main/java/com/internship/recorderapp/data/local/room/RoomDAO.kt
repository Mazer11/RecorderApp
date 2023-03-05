package com.internship.recorderapp.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.internship.recorderapp.data.dataclasses.StoredRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Insert(entity = StoredRecord::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewRecord(record: StoredRecord)

    @Query("SELECT * FROM stored_records")
    fun selectAll(): Flow<List<StoredRecord>>

    @Query("DELETE FROM stored_records WHERE path=:path")
    suspend fun deleteRecord(path: String)
}