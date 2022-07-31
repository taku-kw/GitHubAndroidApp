package com.example.githubandroidapp.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubandroidapp.R
import com.example.githubandroidapp.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val model: UserListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userListView = view.findViewById<RecyclerView>(R.id.userList)
        val userListAdapter = UserListAdapter(view.context, listOf())

        userListView.apply {
            layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = userListAdapter
        }

        model.userList.observe(viewLifecycleOwner, Observer { list ->
            val tempAdapter = userListView.adapter as UserListAdapter
            tempAdapter.setUserList(list)
        })
    }
}