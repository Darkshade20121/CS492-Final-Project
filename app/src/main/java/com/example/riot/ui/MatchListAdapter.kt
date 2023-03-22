package com.example.riot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.riot.R
import com.example.riot.data.MatchData
import com.example.riot.data.Player

class MatchListAdapter(
    private val onMatchClick: (MatchData) -> Unit,
) : RecyclerView.Adapter<MatchListAdapter.MatchViewHolder>() {
    private var matchList = listOf<MatchData>()

    fun updateMatchList(newMatchList: List<MatchData>?) {
        matchList = newMatchList ?: listOf()
        notifyDataSetChanged()
    }

    fun getPlayer(newMatchList: List<MatchData>?, nameMain: String): Player? {
        // Find the player object whose name matches nameMain
        return newMatchList?.flatMap { it.players.all_players }?.find { it.name.equals(nameMain, ignoreCase = true) }
    }

    fun getPlayerProfileLink(newMatchList: List<MatchData>?, nameMain: String): String {
        val player = getPlayer(newMatchList, nameMain)
        return player?.assets?.card?.small ?: ""
    }

    override fun getItemCount() = matchList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_list_item, parent, false)
        return MatchViewHolder(itemView, onMatchClick)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matchList[position])
    }

    class MatchViewHolder(
        itemView: View,
        private val onClick: (MatchData) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val nameTV: TextView = itemView.findViewById(R.id.tv_match_map)
        private var currentMatch: MatchData? = null

        init {
            itemView.setOnClickListener {
                currentMatch?.let(onClick)
            }
        }

        fun bind(matchRepo: MatchData) {
            currentMatch = matchRepo
            nameTV.text = matchRepo.metadata.map

            when (matchRepo.metadata.map) {
                "Ascent" -> nameTV.setBackgroundResource(R.drawable.ascent)
                "Bind" -> nameTV.setBackgroundResource(R.drawable.bind)
                "Breeze" -> nameTV.setBackgroundResource(R.drawable.breeze)
                "Fracture" -> nameTV.setBackgroundResource(R.drawable.fracture)
                "Haven" -> nameTV.setBackgroundResource(R.drawable.haven)
                "Icebox" -> nameTV.setBackgroundResource(R.drawable.icebox)
                "Lotus" -> nameTV.setBackgroundResource(R.drawable.lotus)
                "Pearl" -> nameTV.setBackgroundResource(R.drawable.pearl)
                "Split" -> nameTV.setBackgroundResource(R.drawable.split)
            }

            nameTV.background.alpha = 150
        }
    }
}
