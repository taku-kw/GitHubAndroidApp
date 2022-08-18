package com.example.githubandroidapp.data

data class UserDetail(
    val userName: String,
    val userFullName: String,
    val avatarUrl: String,
    val follower: Int,
    val following: Int,
)