package com.internship.recorderapp.data.usecases

import com.internship.recorderapp.data.dataclasses.StoredRecord
import com.internship.recorderapp.data.local.room.LocalDataRepository

class InsertNewRecordUseCase(
    private val repository: LocalDataRepository
) {
    suspend operator fun invoke(record: StoredRecord) {
        repository.insertNewRecord(record)
    }
}