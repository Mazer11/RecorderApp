package com.internship.recorderapp.data.remote.retrofit

import retrofit2.Call

class RetrofitRepository(
    private val api: RetrofitApi
) {

    fun postRecord(file: String): Call<String> {
        return api.postRecord(file)
    }

}