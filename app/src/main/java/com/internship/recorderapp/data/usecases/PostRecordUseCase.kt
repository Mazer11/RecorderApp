package com.internship.recorderapp.data.usecases

import com.internship.recorderapp.data.remote.retrofit.RetrofitRepository
import retrofit2.Call

class PostRecordUseCase(
    private val repository: RetrofitRepository
) {
    operator fun invoke(file: String): Call<String>{
        return repository.postRecord(file)
    }
}