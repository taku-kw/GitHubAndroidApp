package com.example.githubandroidapp.data.api.getUserList.response

data class GetUserListResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Item>
)
