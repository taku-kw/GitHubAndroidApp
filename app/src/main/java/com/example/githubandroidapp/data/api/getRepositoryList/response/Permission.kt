package com.example.githubandroidapp.data.api.getRepositoryList.response

data class Permission(
    val admin: Boolean,
    val push: Boolean,
    val pull: Boolean,
)
