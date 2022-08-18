package com.example.githubandroidapp.view.user_repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.githubandroidapp.R
import com.example.githubandroidapp.constant.arg.UserRepositoryArg.Companion.USER_NAME
import com.example.githubandroidapp.view.common.Loading
import com.example.githubandroidapp.viewmodel.UserRepositoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserRepositoryFragment : Fragment() {
    private val userName: String
        get() = checkNotNull(arguments?.getString(USER_NAME))

    companion object {
        fun newInstance(userName: String): UserRepositoryFragment {
            return UserRepositoryFragment().apply {
                arguments = Bundle().apply {
                    putString(USER_NAME, userName)
                }
            }
        }
    }

    private val model: UserRepositoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_repository, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.openPage(this.userName)

        Loading.setContext(view.context)

        model.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                Loading.show()
            } else {
                Loading.dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Loading.clearContext()
    }
}