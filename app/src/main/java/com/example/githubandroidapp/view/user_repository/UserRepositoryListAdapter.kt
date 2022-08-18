package com.example.githubandroidapp.view.user_repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubandroidapp.R
import com.example.githubandroidapp.data.Repository

class UserRepositoryListAdapter(private val context: Context, private var repositoryList: MutableList<Repository>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        class DataViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val repositoryName: TextView
            val developLanguage: TextView
            val starImg: ImageView
            val starNum: TextView
            val description: TextView

            init {
                repositoryName = view.findViewById(R.id.repositoryName)
                developLanguage = view.findViewById(R.id.developLanguage)
                starImg = view.findViewById(R.id.starImg)
                starNum = view.findViewById(R.id.starNum)
                description = view.findViewById(R.id.description)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_user_repository_list_item, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DataViewHolder) {
            val repo = repositoryList[position]
            holder.repositoryName.text = repo.name
            holder.developLanguage.text = repo.developLanguage
            holder.starImg.setImageBitmap(getBitmapFromAssets("star.jpg"))
            holder.starNum.text = repo.starNum.toString()
            holder.description.text = repo.description
        }
    }

    override fun getItemCount(): Int = repositoryList.size

    fun setRepositoryList(repositoryList: List<Repository>) {
        this.repositoryList = repositoryList.toMutableList()
        notifyDataSetChanged()
    }

    private fun getBitmapFromAssets(path: String) : Bitmap {
        val inputStream = context.assets.open(path)
        return BitmapFactory.decodeStream(inputStream)
    }
}