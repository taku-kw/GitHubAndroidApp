package com.example.githubandroidapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubandroidapp.data.User
import com.example.githubandroidapp.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {

    val userList = MutableLiveData<MutableList<User>>(mutableListOf())
    val isLoading = MutableLiveData<Boolean>()

    private var searchWord = ""
    private var page = 0
    private val perPage = 20
    private var totalCount = 0

    fun searchUser(searchWord: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            commonSearchUser(searchWord)
            isLoading.postValue(false)
        }
    }

    fun searchNextUser() {
        viewModelScope.launch(Dispatchers.IO) {
            if (++page * perPage < totalCount) {
                commonSearchUser(searchWord, page, userList.value!!)
            }
        }
    }

    fun reset() {
        userList.postValue(mutableListOf())
    }

    private suspend fun commonSearchUser(
        searchWord: String,
        page: Int = 1,
        existingUserList: MutableList<User> = mutableListOf(),
    ) {
        try {
            val data = githubRepository.getUserList(searchWord, page, perPage)
            userList.postValue((existingUserList + data.list).toMutableList())

            this@UserListViewModel.searchWord = searchWord
            this@UserListViewModel.page = page
            this@UserListViewModel.totalCount = data.totalCount
        } catch (e: Exception) {
            Log.w("UserListViewModel.searchUser($searchWord, $page)", e.toString())
        }
    }
}