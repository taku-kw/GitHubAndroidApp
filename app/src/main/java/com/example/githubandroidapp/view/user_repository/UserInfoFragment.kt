package com.example.githubandroidapp.view.user_repository

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.githubandroidapp.R
import com.example.githubandroidapp.viewmodel.UserRepositoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView

@AndroidEntryPoint
class UserInfoFragment : Fragment() {
    private val model: UserRepositoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val avatarImage = view.findViewById<CircleImageView>(R.id.avatarImage)
        val userName = view.findViewById<TextView>(R.id.userName)
        val userFullName = view.findViewById<TextView>(R.id.userFullName)
        val followerNum = view.findViewById<TextView>(R.id.followerNum)
        val followingNum = view.findViewById<TextView>(R.id.followingNum)

        model.userDetail.observe(viewLifecycleOwner) { userDetail ->
            avatarImage.load(Uri.parse(userDetail.avatarUrl))
            userName.text = userDetail.userName
            userFullName.text = userDetail.userFullName
            followerNum.text = userDetail.follower.toString()
            followingNum.text = userDetail.following.toString()
        }
    }
}