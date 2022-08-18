package com.example.githubandroidapp.view.main

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.githubandroidapp.R
import com.example.githubandroidapp.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFormFragment : Fragment() {

    private val model: UserListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchForm = view.findViewById<EditText>(R.id.searchForm)
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        searchForm.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                inputMethodManager.hideSoftInputFromWindow(
                    searchForm.windowToken,
                    InputMethodManager.RESULT_UNCHANGED_SHOWN
                )
                model.reset()
                model.searchUser(searchForm.text.toString())
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }
}