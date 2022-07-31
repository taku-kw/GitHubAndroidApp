package com.example.githubandroidapp.api

import com.example.githubandroidapp.api.service.GithubService
import com.example.githubandroidapp.data.api.getUserList.response.GetUserListResponse
import java.lang.Exception
import javax.inject.Inject

interface GithubApi {
    suspend fun getUserList(
        query: String,
        page: Int,
        perPage: Int,
    ): GetUserListResponse
}

class GithubApiImpl @Inject constructor(
    private val service: GithubService
) : GithubApi {

    override suspend fun getUserList(
        query: String,
        page: Int,
        perPage: Int,
    ): GetUserListResponse {
        return apiCall(service.getUserList(query, page, perPage)).body()
            ?: throw Exception("Response Body is null")
    }
}