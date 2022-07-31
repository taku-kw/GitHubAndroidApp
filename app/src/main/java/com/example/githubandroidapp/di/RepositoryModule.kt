package com.example.githubandroidapp.di

import com.example.githubandroidapp.repository.GithubRepository
import com.example.githubandroidapp.repository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGithubRepository(impl: GithubRepositoryImpl): GithubRepository
}