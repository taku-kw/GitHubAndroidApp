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
import com.example.githubandroidapp.data.RepositoryView
import com.example.githubandroidapp.data.repositoryLoadingView
import com.example.githubandroidapp.enum.ViewType
import com.example.githubandroidapp.view.common.ScrollAdapter

class UserRepositoryListAdapter(private val context: Context, private var repositoryList: MutableList<RepositoryView>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), ScrollAdapter {

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

    class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var clickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(repo: Repository)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.VIEW_TYPE_DATA.ordinal) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_user_repository_list_item, parent, false)
            DataViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.loading_list_item, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DataViewHolder) {
            val repo = repositoryList[position].repository
            holder.repositoryName.text = repo.name
            holder.developLanguage.text = repo.developLanguage
            holder.starImg.setImageBitmap(getBitmapFromAssets("star.jpg"))
            holder.starNum.text = repo.starNum.toString()
            holder.description.text = repo.description

            holder.itemView.setOnClickListener {
                clickListener.onItemClick(repo)
            }
        }
    }

    override fun getItemCount(): Int = repositoryList.size

    override fun getItemViewType(position: Int): Int {
        return repositoryList[position].viewType.ordinal
    }

    fun setRepositoryList(repositoryList: List<Repository>) {
        this.repositoryList = convRepositoryViewList(repositoryList)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: UserRepositoryListAdapter.OnItemClickListener) {
        this.clickListener = listener
    }

    override fun addLoadingView() {
        this.repositoryList.add(repositoryLoadingView)
        notifyItemInserted(this.repositoryList.size - 1)
    }

    override fun removeLoadingView() {
        this.repositoryList.removeLast()
        notifyItemRemoved(this.repositoryList.size)
    }

    private fun getBitmapFromAssets(path: String) : Bitmap {
        val inputStream = context.assets.open(path)
        return BitmapFactory.decodeStream(inputStream)
    }

    private fun convRepositoryViewList(repositoryList: List<Repository>) : MutableList<RepositoryView> {
        val repositoryViewList = mutableListOf<RepositoryView>()

        repositoryList.forEach { repo ->
            repositoryViewList.add(
                RepositoryView(repo, ViewType.VIEW_TYPE_DATA)
            )
        }

        return repositoryViewList
    }
}