package com.example.githubandroidapp.view.main

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserListScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val adapter: UserListAdapter,
    val getNextPageFunction: () -> Unit,
    ) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = 2
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        // 下方向にスクロールされた場合
        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (loading) {
                if (totalItemCount > previousTotal) {
                    recyclerView.post {
                        adapter.removeLoadingView()
                    }
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)
            ) {
                recyclerView.post {
                    adapter.addLoadingView()
                }
                getNextPageFunction()
                loading = true
            }
        }
    }
}