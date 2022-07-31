package com.example.githubandroidapp.api.service

import com.example.githubandroidapp.data.api.getUserList.response.GetUserListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("/search/users")
    fun getUserList(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Call<GetUserListResponse>
}