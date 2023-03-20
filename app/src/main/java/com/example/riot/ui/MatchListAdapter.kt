package com.example.riot.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.riot.R
import com.example.riot.data.MatchData

/**
 * This is the constructor for a RecyclerView adapter for lists of GitHub repos.
 *
 * @param onGitHubRepoClick This should be a function for handling a click on an individual repo
 *   in the list of repos managed by this adapter.  When the repo is clicked, a representation of
 *   that repo is passed into this function as a `GitHubRepo` object.
 */
class GitHubRepoListAdapter(
    private val onMatchClick: (MatchData) -> Unit
) : RecyclerView.Adapter<GitHubRepoListAdapter.GitHubRepoViewHolder>() {
    private var gitHubRepoList = listOf<MatchData>()

    /**
     * This method is called to completely replace the list of repositories being managed by an
     * adapter.
     *
     * @param newRepoList A new list of GitHub repositories to replace the one being managed by
     *   this adapter.
     */
    fun updateRepoList(newRepoList: List<MatchData>?) {
        gitHubRepoList = newRepoList ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = gitHubRepoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubRepoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_list_item, parent, false)
        return GitHubRepoViewHolder(itemView, onMatchClick)
    }

    override fun onBindViewHolder(holder: GitHubRepoViewHolder, position: Int) {
        holder.bind(gitHubRepoList[position])
    }

    class GitHubRepoViewHolder(
        itemView: View,
        private val onClick: (MatchData) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val nameTV: TextView = itemView.findViewById(R.id.tv_name)
        private var currentGitHubRepo: MatchData? = null

        /*
         * Set up a click listener on this individual ViewHolder.  Call the provided onClick
         * function, passing the repo represented by this ViewHolder as an argument.
         */
        init {
            itemView.setOnClickListener {
                currentGitHubRepo?.let(onClick)
            }
        }

        fun bind(gitHubRepo: MatchData) {
            currentGitHubRepo = gitHubRepo
            nameTV.text = gitHubRepo.name
        }
    }
}