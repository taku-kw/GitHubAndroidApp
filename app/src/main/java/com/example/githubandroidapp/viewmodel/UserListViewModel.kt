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

    private var searchWord = ""
    private var page = 0
    private val perPage = 20
    private var totalCount = 0

    fun searchUser(searchWord: String, page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = githubRepository.getUserList(searchWord, page, perPage)
                userList.postValue(data.list.toMutableList())

                this@UserListViewModel.searchWord = searchWord
                this@UserListViewModel.page = page
                this@UserListViewModel.totalCount = data.totalCount
            } catch (e: Exception) {
                Log.w("UserListViewModel.searchUser($searchWord, $page)", e.toString())
            }
        }
    }
}