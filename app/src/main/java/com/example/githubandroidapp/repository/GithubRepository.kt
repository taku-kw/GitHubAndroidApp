package com.example.githubandroidapp.repository

import com.example.githubandroidapp.api.GithubApi
import com.example.githubandroidapp.data.User
import com.example.githubandroidapp.data.UserList
import javax.inject.Inject

interface GithubRepository {
    suspend fun getUserList(
        query: String,
        page: Int,
        perPage: Int,
    ): UserList
}

class GithubRepositoryImpl @Inject constructor(private val githubApi: GithubApi) :
    GithubRepository {

    override suspend fun getUserList(query: String, page: Int, perPage: Int): UserList {
        val userList = mutableListOf<User>()

        val response = githubApi.getUserList(query, page, perPage)

        response.items.forEach { data ->
            userList.add(
                User(
                    data.login,
                    data.avatar_url,
                    data.repos_url,
                )
            )
        }

        return UserList(response.total_count, userList)
    }
}