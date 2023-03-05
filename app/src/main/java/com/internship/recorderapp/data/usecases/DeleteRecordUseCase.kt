package com.internship.recorderapp.data.usecases

import com.internship.recorderapp.data.local.room.LocalDataRepository

class DeleteRecordUseCase(
    private val repository: LocalDataRepository
) {
    suspend operator fun invoke(path: String) {
        repository.deleteRecord(path)
    }
}