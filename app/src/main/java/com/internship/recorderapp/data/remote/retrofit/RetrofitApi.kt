package com.internship.recorderapp.data.remote.retrofit

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitApi {

    @POST("/method/docs.save")
    fun postRecord(
        @Query("file") file: String,
    ): Call<String>

}