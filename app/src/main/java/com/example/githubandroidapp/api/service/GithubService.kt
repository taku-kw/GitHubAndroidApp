package com.example.githubandroidapp.api.service

import com.example.githubandroidapp.data.api.getRepositoryList.response.GetRepositoryList
import com.example.githubandroidapp.data.api.getUser.response.GetUserResponse
import com.example.githubandroidapp.data.api.getUserList.response.GetUserListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("/search/users")
    fun getUserList(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Call<GetUserListResponse>

    @GET("/users/{user_name}")
    fun getUser(
        @Path("user_name") userName: String,
    ) : Call<GetUserResponse>

    @GET("/users/{user_name}/repos")
    fun getRepositoryList(
        @Path("user_name") userName: String,
    ) : Call<List<GetRepositoryList>>
}