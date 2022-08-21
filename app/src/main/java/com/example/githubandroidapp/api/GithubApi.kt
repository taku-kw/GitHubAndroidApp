package com.example.githubandroidapp.api

import com.example.githubandroidapp.api.service.GithubService
import com.example.githubandroidapp.data.api.getRepositoryList.response.GetRepositoryList
import com.example.githubandroidapp.data.api.getUser.response.GetUserResponse
import com.example.githubandroidapp.data.api.getUserList.response.GetUserListResponse
import javax.inject.Inject

interface GithubApi {
    suspend fun getUserList(
        query: String,
        page: Int,
        perPage: Int,
    ): GetUserListResponse

    suspend fun getUser(
        userName: String,
    ): GetUserResponse

    suspend fun getRepositoryList(
        userName: String,
        page: Int,
        perPage: Int,
    ): List<GetRepositoryList>
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

    override suspend fun getUser(
        userName: String,
    ): GetUserResponse {
        return apiCall(service.getUser(userName)).body()
            ?: throw Exception("Response Body is null")
    }

    override suspend fun getRepositoryList(
        userName: String,
        page: Int,
        perPage: Int,
    ): List<GetRepositoryList> {
        return apiCall(service.getRepositoryList(userName, page, perPage)).body()
            ?: throw Exception("Response Body is null")
    }
}