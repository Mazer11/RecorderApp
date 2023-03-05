package com.internship.recorderapp.data.usecases

data class RecordsUseCases(
    val insertNewRecordUseCase: InsertNewRecordUseCase,
    val selectAllUseCase: SelectAllUseCase,
    val deleteRecordUseCase: DeleteRecordUseCase
)