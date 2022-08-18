package com.example.githubandroidapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubandroidapp.data.Repository
import com.example.githubandroidapp.data.UserDetail
import com.example.githubandroidapp.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRepositoryViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    val userDetail = MutableLiveData<UserDetail>()
    val repositoryList = MutableLiveData<List<Repository>>(mutableListOf())
    val isLoading = MutableLiveData(false)

    fun openPage(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            val jobSearchUserDetail = async { searchUserDetail(userName) }
            val jobSearchRepository = async { searchRepository(userName) }
            jobSearchUserDetail.await()
            jobSearchRepository.await()
            isLoading.postValue(false)
        }
    }

    private suspend fun searchUserDetail(userName: String) {
        try {
            val data = githubRepository.getUser(userName)
            userDetail.postValue(data)
        } catch (e: Exception) {
            Log.w("UserRepositoryViewModel.searchUserDetail(userName=$userName)", e.toString())
        }
    }

    private suspend fun searchRepository(userName: String) {
        try {
            val data = githubRepository.getRepositoryList(userName)
            repositoryList.postValue(data)
        } catch (e: Exception) {
            Log.w("UserRepositoryViewModel.searchUserRepository(userName=$userName)", e.toString())
        }
    }
}