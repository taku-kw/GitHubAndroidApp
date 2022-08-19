package com.example.githubandroidapp.repository

import com.example.githubandroidapp.api.GithubApi
import com.example.githubandroidapp.data.Repository
import com.example.githubandroidapp.data.User
import com.example.githubandroidapp.data.UserDetail
import com.example.githubandroidapp.data.UserList
import javax.inject.Inject

interface GithubRepository {
    suspend fun getUserList(
        query: String,
        page: Int,
        perPage: Int,
    ): UserList

    suspend fun getUser(
        userName: String,
    ): UserDetail

    suspend fun getRepositoryList(
        userName: String,
    ): List<Repository>
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

    override suspend fun getUser(userName: String): UserDetail {
        val response = githubApi.getUser(userName)

        return UserDetail(
            response.login,
            response.name,
            response.avatar_url,
            response.followers,
            response.following,
        )
    }

    override suspend fun getRepositoryList(userName: String): List<Repository> {
        val repositoryList = mutableListOf<Repository>()

        val response = githubApi.getRepositoryList(userName)

        response.forEach {
            repositoryList.add(
                Repository(
                    it.name,
                    it.language,
                    it.stargazers_count,
                    it.description,
                    it.html_url,
                    it.fork,
                )
            )
        }

        return repositoryList
    }
}