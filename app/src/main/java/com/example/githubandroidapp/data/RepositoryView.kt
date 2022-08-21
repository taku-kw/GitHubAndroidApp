package com.example.githubandroidapp.data

import com.example.githubandroidapp.enum.ViewType

data class RepositoryView (
    val repository: Repository,
    val viewType: ViewType,
)

val repositoryLoadingView = RepositoryView(
    Repository("", "", 0, "", "", false),
    ViewType.VIEW_TYPE_LOADING,
)