package com.example.githubandroidapp.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubandroidapp.R

class UserListFragment : Fragment() {

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
    }
}