package com.example.githubandroidapp.di

import com.example.githubandroidapp.api.GithubApi
import com.example.githubandroidapp.api.GithubApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    @Binds
    @Singleton
    abstract fun bindGithubApi(impl: GithubApiImpl): GithubApi
}