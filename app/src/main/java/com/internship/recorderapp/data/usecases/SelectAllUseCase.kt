package com.internship.recorderapp.data.usecases

import com.internship.recorderapp.data.dataclasses.StoredRecord
import com.internship.recorderapp.data.local.room.LocalDataRepository
import kotlinx.coroutines.flow.Flow

class SelectAllUseCase(
    private val repository: LocalDataRepository
) {
    operator fun invoke(): Flow<List<StoredRecord>> {
        return repository.selectAll()
    }
}