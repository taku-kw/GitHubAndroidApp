package com.example.githubandroidapp.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubandroidapp.R
import com.example.githubandroidapp.data.User
import com.example.githubandroidapp.view.common.Loading
import com.example.githubandroidapp.view.user_repository.UserRepositoryFragment
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
        val userListAdapter = UserListAdapter(mutableListOf())
        userListAdapter.setOnItemClickListener(
            object : UserListAdapter.OnItemClickListener {
                override fun onItemClick(user: User) {
                    parentFragment!!.parentFragmentManager.beginTransaction()
                        .replace(R.id.mainFragmentContainerView, UserRepositoryFragment.newInstance(user.name))
                        .addToBackStack(null)
                        .commit()
                }
            }
        )

        userListView.apply {
            val linearLayoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            layoutManager = linearLayoutManager
            adapter = userListAdapter
            addOnScrollListener(UserListScrollListener(linearLayoutManager, this.adapter as UserListAdapter) {
                model.searchNextUser()
            })
        }

        model.userList.observe(viewLifecycleOwner) { list ->
            val tempAdapter = userListView.adapter as UserListAdapter
            tempAdapter.setUserList(list)
        }

        Loading.setContext(view.context)

        model.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                Loading.show()
            } else {
                Loading.dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        Loading.setContext(view!!.context)
    }
}