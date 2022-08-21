package com.example.githubandroidapp.view.main

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.githubandroidapp.R
import com.example.githubandroidapp.data.User
import com.example.githubandroidapp.data.UserView
import com.example.githubandroidapp.data.userLoadingView
import com.example.githubandroidapp.enum.ViewType
import com.example.githubandroidapp.view.common.ScrollAdapter
import de.hdodenhof.circleimageview.CircleImageView

class UserListAdapter(private var userList: MutableList<UserView>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), ScrollAdapter {

    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatarImage: CircleImageView
        val userName: TextView

        init {
            avatarImage = view.findViewById(R.id.avatarImage)
            userName = view.findViewById(R.id.userName)
        }
    }

    class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    private lateinit var clickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.VIEW_TYPE_DATA.ordinal) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_user_list_item, parent, false)
            DataViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.loading_list_item, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DataViewHolder) {
            val user = userList[position].user
            holder.avatarImage.load(Uri.parse(user.avatarUrl))
            holder.userName.text = user.name

            holder.itemView.setOnClickListener {
                clickListener.onItemClick(user)
            }
        }
    }

    override fun getItemCount(): Int = userList.size

    override fun getItemViewType(position: Int): Int {
        return userList[position].viewType.ordinal
    }

    fun setUserList(userList: List<User>) {
        this.userList = convUserViewList(userList)
        notifyDataSetChanged()
    }

    override fun addLoadingView() {
        this.userList.add(userLoadingView)
        notifyItemInserted(this.userList.size - 1)
    }

    override fun removeLoadingView() {
        this.userList.removeLast()
        notifyItemRemoved(this.userList.size)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.clickListener = listener
    }

    private fun convUserViewList(userList: List<User>) : MutableList<UserView> {
        val userViewList = mutableListOf<UserView>()

        userList.forEach { user ->
            userViewList.add(
                UserView(user, ViewType.VIEW_TYPE_DATA)
            )
        }

        return userViewList
    }
}