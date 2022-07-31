package com.example.githubandroidapp.view.main

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.githubandroidapp.R
import com.example.githubandroidapp.data.User
import de.hdodenhof.circleimageview.CircleImageView

class UserListAdapter(private val context: Context, private var userList: List<User>) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatarImage: CircleImageView
        val userName: TextView

        init {
            avatarImage = view.findViewById(R.id.avatarImage)
            userName = view.findViewById(R.id.userName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_user_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.avatarImage.load(Uri.parse(userList[position].avatarUrl))
        holder.userName.text = userList[position].name
    }

    override fun getItemCount(): Int = userList.size

    fun setUserList(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}