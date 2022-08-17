package com.example.githubandroidapp.data

import com.example.githubandroidapp.enum.ViewType

data class UserView(
    val user: User,
    val viewType: ViewType,
)

val userLoadingView = UserView(
    User("", "", ""),
    ViewType.VIEW_TYPE_LOADING,
)