package com.example.githubandroidapp.view.user_repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubandroidapp.R
import com.example.githubandroidapp.data.Repository
import com.example.githubandroidapp.view.common.ScrollListener
import com.example.githubandroidapp.viewmodel.UserRepositoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserRepositoryListFragment : Fragment() {
    private val model: UserRepositoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_repository_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repositoryListView = view.findViewById<RecyclerView>(R.id.repositoryList)
        val repositoryListAdapter = UserRepositoryListAdapter(view.context, mutableListOf())

        repositoryListAdapter.setOnItemClickListener(
            object : UserRepositoryListAdapter.OnItemClickListener {
                override fun onItemClick(repo: Repository) {
                    val tabsIntent = CustomTabsIntent.Builder().build()
                    tabsIntent.launchUrl(view.context, repo.url.toUri())
                }
            }
        )

        repositoryListView.apply {
            val linearLayoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            layoutManager = linearLayoutManager
            adapter = repositoryListAdapter
            addOnScrollListener(ScrollListener(linearLayoutManager, this.adapter as UserRepositoryListAdapter) {
                model.searchNextRepository()
            })
        }

        model.repositoryList.observe(viewLifecycleOwner) { list ->
            val tempAdapter = repositoryListView.adapter as UserRepositoryListAdapter
            tempAdapter.setRepositoryList(list)
        }
    }
}